package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;

import user.User;
import user.exceptions.UserException;

public class UserDAO {

	private static final String INSERT_USER_SQL = "INSERT INTO users VALUES (null, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_USER_SQL = "DELETE FROM users WHERE email = ?";
	private static final String SET_PHONE_NUMBER_SQL = "UPDATE users SET phone_number = ? WHERE email = ?";
	private static final String SET_BIRTHDAY_DATE_SQL = "UPDATE users SET birthday = ? WHERE email = ?";
	private static final String GET_USER_ID_BY_EMAIL_SQL = "SELECT user_id FROM users WHERE email = ?";
	private static final String INSERT_FRIEND_SQL = "INSERT INTO friends VALUES (?, ?)";
	private static final String DELETE_FRIEND_SQL = "DELETE FROM friends WHERE user1_id = ? AND user2_id= ?";

	public int registerUser(User user) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getPhoneNumber());
			// TODO moje i da bachka
			// LocalDate ld = user.getBirthdayDate();
			// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd
			// HH:mm:ss");
			// Timestamp ts = Timestamp.valueOf(ld.toString());
			ps.setTimestamp(6, null);

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			System.out.println("==========" + rs.getInt(1));
			// user.setUserID(rs.getInt(1));
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new UserException("User cannot be registered now, please try again later.", e);
		}
	}

	public void deleteUser(User user) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(DELETE_USER_SQL);
			ps.setString(1, user.getEmail());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new UserException("User cannot be deleted now, please try again later.", e);
		}
	}

	public void setPhoneNumber(User user, String phoneNumber) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(SET_PHONE_NUMBER_SQL);
			pstmt.setString(1, phoneNumber);
			pstmt.setString(2, user.getEmail());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UserException("You cannot update your phone number rigth now! Please try again later! ");
		}
	}

	public void setBirthdayDate(User user, LocalDate birthdayDate) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(SET_BIRTHDAY_DATE_SQL);
			Timestamp timestamp = Timestamp.valueOf(birthdayDate.atStartOfDay());
			pstmt.setTimestamp(1, timestamp);
			pstmt.setString(2, user.getEmail());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UserException("You cannot update your birthday date rigth now! Please try again later! ");
		}
	}

	public void addNewFriendDB(User friend, User user) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			int user1_id = friend.getUserID();
			int user2_id = user.getUserID();
			PreparedStatement pstmt = connection.prepareStatement(INSERT_FRIEND_SQL);
			pstmt.setInt(2, user1_id);
			pstmt.setInt(1, user2_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("You cannot add new chat rigth now! Please try again later! ");
		}
	}

	public void removeFriendDB(User friend, User user) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			connection.setAutoCommit(false);
			new ChatDAO().deleteChat(user, friend);
			PreparedStatement pstmt = connection.prepareStatement(DELETE_FRIEND_SQL);
			pstmt.setInt(1, user.getUserID());
			pstmt.setInt(2, friend.getUserID());
			int count = pstmt.executeUpdate();
			if (count == 0) {
				connection.rollback();
				removeFriendDB(user, friend);
			} else {
				connection.setAutoCommit(true);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("You cannot remove friend rigth now! Please try again later! ");
		}
	}

}

package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import chat.Chat;
import chat.Message;
import user.User;
import user.exceptions.UserException;

public class ChatDAO {

	private static final String INSERT_CHAT_SQL = "INSERT INTO chats VALUES (null, ?, ?)";
	private static final String INSERT_MESSAGE_SQL = "INSERT INTO messages VALUES (null, ?, ?, ?)";
	private static final String DELETE_MESSAGES_SQL = "DELETE FROM messages where chat_id = ? ";
	private static final String DELETE_CHAT_SQL = "DELETE FROM chats where chat_id = ? ";

	public void addNewChatDB(Chat chat, User user1, User user2) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {

			PreparedStatement pstmt = connection.prepareStatement(INSERT_CHAT_SQL, Statement.RETURN_GENERATED_KEYS);
			int user1_id = user1.getUserID();
			int user2_id = user2.getUserID();

			pstmt.setInt(1, user1_id);
			pstmt.setInt(2, user2_id);

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			chat.setId(rs.getInt(1));
			System.err.println("chatid: "+chat.getId());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("You cannot add new chat rigth now! Please try again later! ");
		}
	}

	public void addNewMessage(Chat chat, Message message) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			System.err.println("CHAT ID V ADD NEW MESG: "+ chat.getId());
			PreparedStatement pstmt = connection.prepareStatement(INSERT_MESSAGE_SQL);
			Timestamp timestamp = Timestamp.valueOf(message.getTimeOfTheMessage());
			pstmt.setTimestamp(1, timestamp);
			pstmt.setString(2, message.getMessage());
			pstmt.setInt(3, chat.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("You cannot add new message rigth now! Please try again later! ");
		}
	}

	private void deleteMessages(Chat chat) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement(DELETE_MESSAGES_SQL);
			pstmt.setInt(1, chat.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("You cannot delete messages rigth now! Please try again later! ", e);
		}

	}

	public void deleteChat(User user, User friend) throws UserException {

		try {
			Chat chat = user.getChatbyUser(friend);
			int chatId = chat.getId();
			System.err.println("chatid: "+chatId);
			deleteMessages(chat);
			Connection connection = DBConnection.getInstance().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(DELETE_CHAT_SQL);
			pstmt.setInt(1, chatId);
			pstmt.executeUpdate();

		} catch (SQLException | UserException e) {
			e.printStackTrace();
			throw new UserException("You cannot delete chat rigth now! Please try again later! ", e);
		}
	}
}
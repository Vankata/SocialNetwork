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
	
	public void addNewChatDB(Chat chat, User user1, User user2) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();
		
		try{
		PreparedStatement pstmt = connection.prepareStatement(INSERT_CHAT_SQL, Statement.RETURN_GENERATED_KEYS);
		int user1_id = user1.getUserID();
		int user2_id = user2.getUserID();

		pstmt.setInt(1, user1_id);
		pstmt.setInt(2, user2_id);

		pstmt.executeUpdate();
		
		ResultSet rs = pstmt.getGeneratedKeys();
		rs.next();
		chat.setId(rs.getInt(1));
		}catch(SQLException e){
			e.printStackTrace();
			throw new UserException("You cannot add new chat rigth now! Please try again later! ");
		}
	}

	public void addNewMessage(Chat chat, int userID, Message message) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();
		
		try{
			PreparedStatement pstmt = connection.prepareStatement(INSERT_MESSAGE_SQL);
			Timestamp timestamp = Timestamp.valueOf(message.getTimeOfTheMessage());
			pstmt.setTimestamp(1, timestamp);
			pstmt.setString(2, message.getMessage());
			pstmt.setInt(3, userID);
			
			pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new UserException("You cannot add new message rigth now! Please try again later! ");
		}
	}
}
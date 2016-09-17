package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import user.User;
import user.exceptions.UserException;
import wall.Photo;
import wall.Post;
import wall.exceptions.PostException;

public class PostDAO {
	private static final String INSERT_POST_SQL = "INSERT INTO posts VALUES (null, ?, ?,?,?,?)";
	private static final String INSERT_COMMENT_SQL = "INSERT INTO comments VALUES (null, ?, ?, ?)";


	public int addPost(User user, Post post) throws PostException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(INSERT_POST_SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, post.getNumberOfLikes());
			ps.setInt(2, post.getNumberOfComments());
			ps.setString(3, post.getText());
			if (((Photo) post).isPhoto()) {
				ps.setString(4, ((Photo) post).getPathToThePhoto());
			} else {
				ps.setString(4, null);
			}
			ps.setInt(5, user.getUserID());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			post.setPostID(rs.getInt(1));
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new PostException("Post cannot be added now, please try again later.", e);
		}
	}
	public void commentPost(User user,Post post, String comment) throws PostException {
	Connection connection = DBConnection.getInstance().getConnection();
	try {
		PreparedStatement pstmt = connection.prepareStatement(INSERT_COMMENT_SQL,Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, comment);
		pstmt.setInt(2, post.getPostID());
		pstmt.setInt(3, user.getUserID());
		pstmt.executeUpdate();
	} catch (SQLException e) {
		throw new PostException("You cannot comment rigth now! Please try again later! ");
	}

}

}

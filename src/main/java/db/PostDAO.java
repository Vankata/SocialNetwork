package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.RowDataCursor;

import user.User;
import user.exceptions.UserException;
import wall.Photo;
import wall.Post;
import wall.exceptions.PostException;

public class PostDAO {
	private static final String ALREADY_LIKED_SQL = "SELECT EXISTS(SELECT * FROM likes WHERE user_id = ? and post_id = ?)";
	private static final String INSERT_POST_SQL = "INSERT INTO posts VALUES (null, ?, ?, ?, ?, ?)";
	private static final String INSERT_COMMENT_SQL = "INSERT INTO comments VALUES (null, ?, ?, ?)";
	private static final String INSERT_LIKE_SQL = "INSERT INTO likes VALUES (?, ?)";
	private static final String DELETE_LIKE_SQL = "DELETE FROM likes WHERE user_id = ? AND post_id = ?";;

	public int addPost(User user, Post post) throws PostException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(INSERT_POST_SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, post.getNumberOfLikes());
			ps.setInt(2, post.getNumberOfComments());
			System.err.println(post.getText());
			ps.setString(3, post.getText());
			if (post.isPhoto()) {
				ps.setString(4, ((Photo) post).getPathToThePhoto());
			} else {
				ps.setString(4, null);
			}
			System.out.println("userId = " + user.getUserID());
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

	public void commentPost(User user, Post post, String comment) throws PostException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement(INSERT_COMMENT_SQL, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, comment);

			pstmt.setInt(2, post.getPostID());

			pstmt.setInt(3, user.getUserID());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new PostException("You cannot like this post rigth now! Please try again later! ", e);
		}

	}

	public void liketPost(User user, Post post) throws PostException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement pstmExists = connection.prepareStatement(ALREADY_LIKED_SQL);
			pstmExists.setInt(1, user.getUserID());
			pstmExists.setInt(2, post.getPostID());
			ResultSet resultSet = pstmExists.executeQuery();
			resultSet.next();

			PreparedStatement pstmt = null;

			if (resultSet.getInt(1) == 1) {
				pstmt = connection.prepareStatement(DELETE_LIKE_SQL, Statement.RETURN_GENERATED_KEYS);
			} else {
				pstmt = connection.prepareStatement(INSERT_LIKE_SQL, Statement.RETURN_GENERATED_KEYS);
			}

			pstmt.setInt(1, user.getUserID());
			pstmt.setInt(2, post.getPostID());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new PostException("You cannot like it  rigth now! Please try again later! ", e);
		}

	}

}

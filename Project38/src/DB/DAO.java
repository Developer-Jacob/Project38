package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.User;

public class DAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "SCOTT";
	private String pw = "TIGER";

	private final String USER_ID = "ID";
	private final String USER_PSW = "PASSWORD";
	private final String USER_NIC = "NICKNAME";
	private final String USER_MONEY = "MONEY";
	private final String USER_TABLE = "USERS";

	public DAO() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean insert(User user) {
		PreparedStatement pstmt = null;
		String sql = null;
		boolean returnVal = true;
		try {
			conn = DriverManager.getConnection(url, id, pw);
			sql = "INSERT INTO " + USER_TABLE + " VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getNickName());
			pstmt.setInt(4, user.getMoney());
			int result = pstmt.executeUpdate();
			if (result != 1) {
				returnVal = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnVal = false;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnVal;
	}

	public boolean updateMoney(String user_nic, int user_money) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean returnVal = true;

		try {
			conn = DriverManager.getConnection(url, id, pw);
			sql = "UPDATE " + USER_TABLE + " SET " + USER_MONEY + " = ? " + "WHERE " + USER_NIC + " = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_money);
			pstmt.setString(2, user_nic);
			int result = pstmt.executeUpdate();
			if (result != 1) {
				returnVal = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnVal;
	}

	public boolean delete(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean returnVal = true;

		try {
			conn = DriverManager.getConnection(url, id, pw);
			sql = "DELETE FROM " + USER_TABLE + " WHERE " + USER_ID + " = ? AND " + USER_PSW + " =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			int result = pstmt.executeUpdate();
			if (result != 1) {
				returnVal = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnVal;
	}

	public ArrayList<User> selectAll() {
		ArrayList<User> list = new ArrayList<User>();
		Connection con = null;

		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(url, id, pw);
			sql = "SELECT " + USER_NIC + " , " + USER_MONEY + " FROM " + USER_TABLE + " ORDER BY " + USER_MONEY
					+ " DESC";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getString(USER_NIC));
				user.setMoney(rs.getInt(USER_MONEY));
				list.add(user);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}

	public User select(String user_id, String user_psw) {
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {

			con = DriverManager.getConnection(url, id, pw);

			sql =	" SELECT " + USER_NIC + " , " + USER_MONEY + 
					" FROM " + USER_TABLE + 
					" WHERE " + USER_ID + " = ? AND "+ USER_PSW + " = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_psw);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setNickName(rs.getString(USER_NIC));
				user.setMoney(rs.getInt(USER_MONEY));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}
}

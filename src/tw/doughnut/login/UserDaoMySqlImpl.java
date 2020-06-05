package tw.doughnut.login;

import static tw.doughnut.main.Common.CLASS_NAME;
import static tw.doughnut.main.Common.PASSWORD;
import static tw.doughnut.main.Common.URL;
import static tw.doughnut.main.Common.USER;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.doughnut.login.User;
import tw.doughnut.login.UserDao;
//User Data Access Object
public class UserDaoMySqlImpl implements UserDao {

	public UserDaoMySqlImpl() {
		super();
		try {
			Class.forName(CLASS_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	//註冊帳號
	@Override
		public int insert(User user) {
			int count = 0;
			String sql = "INSERT INTO `member` (mem_name, mem_password, mem_email, mem_phone, mem_state, mem_tax) VALUES(?, ?, ?, ?, ?, ?);";
			Connection connection = null;
			PreparedStatement ps = null;
			try {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				ps = connection.prepareStatement(sql);
				ps.setString(1, user.getMemName());
				ps.setString(2, user.getMemPassword());
				ps.setString(3, user.getMemEmail());
				ps.setString(4, user.getMemPhone());
				ps.setInt(5, user.getMemState());
				ps.setString(6, user.getMemTax());
				count = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (ps != null) {

						ps.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return count;
		}

	
	//判斷帳號密碼是否一致
	@Override
	public boolean login(User user) {
		boolean success = false;
		String sql = "SELECT mem_id FROM `member` " + 
					"WHERE mem_email = ? and mem_password = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getMemEmail());
			ps.setString(2, user.getMemPassword());
			ResultSet rs = ps.executeQuery();
			success = rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	
	//取得全部資料
	@Override
	public List<User> getAll() {
		String sql = "SELECT mem_id, mem_name, mem_password, mem_email, mem_phone, mem_joindate, mem_suspendtime, mem_state, mem_tax, mem_address, latitude, longtitude  FROM `member` ORDER BY mem_joindate DESC;";
		Connection connection = null;
		PreparedStatement ps = null;
		List<User> userList = new ArrayList<User>();
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int mem_id = rs.getInt(1);
				String mem_name = rs.getString(2);
				String mem_password = rs.getString(3);
				String mem_email = rs.getString(4);
				String mem_phone = rs.getString(5);
				Date mem_joindate = rs.getTimestamp(6);
				Date mem_suspendtime = rs.getTimestamp(7);
				int mem_state = rs.getInt(8);
				String mem_tax = rs.getString(9);
				String mem_address = rs.getString(10);
				double latitude = rs.getDouble(11);
				double longitude = rs.getDouble(12);
 
 				User user = new User(mem_id, mem_name, mem_password, mem_email, mem_phone, mem_joindate,
						mem_suspendtime, mem_state, mem_tax, mem_address, latitude, longitude);
				userList.add(user);
			}

			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("output getAll return sql:" + userList);
		return userList;
	}
}
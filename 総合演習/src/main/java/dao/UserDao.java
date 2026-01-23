package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.User;

public class UserDao {
	//SELECT文
	public List<User> findAll() {
		//リストを生成
		List<User> userList = new ArrayList<User>();

		//DataSourceの取得
		InitialContext ic;
		DataSource ds = null;

		try {
			ic = new InitialContext();

			//DBの場所
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/calendar");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		//データベース接続
		try (Connection conn = ds.getConnection()) {
			//SELECT文の準備
			String sql = "SELECT userId, passwd, userName, icon FROM USER ORDER BY userId DESC;";
			PreparedStatement ps = conn.prepareStatement(sql);

			//SELECT文を実行し、結果を取得
			ResultSet rs = ps.executeQuery();

			//SELECT文を取得後、AllayListに格納
			while (rs.next()) {
				int userId = rs.getInt("userId");
				String passwd = rs.getString("passwd");
				String userName = rs.getString("userName");
				int icon = rs.getInt("icon");
				User user = new User(userId, passwd, userName, icon);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return userList;
	}
	
	
	//INSERT文
	public boolean insertUser(User user) {
		//DataSourceの取得
		InitialContext ic;
		DataSource ds = null;
		
		try {
			ic = new InitialContext();
			System.out.println("login.try");
			//DBの場所
			ds = (DataSource)ic.lookup("java:comp/env/jdbc/calendar");
		}catch(NamingException e) {
			System.out.println("login.catch");
			e.printStackTrace();
		}
		
		
		//データベース接続
		try(Connection con = ds.getConnection()){
			//INSERT文の準備
			String sql = "INSERT INTO USER(USER_ID, PASSWD, USERNAME, ICON) VALUES(?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql);
			
			//INSERT文の?の部分の値を指定
			
		try(Connection conn = ds.getConnection()) {
			System.out.println("try");
			//SELECT文の準備
			String sql = "SELECT userId, passwd, userName, icon FROM USER WHERE ID = (?) AND PASSWORD = (?) ;";
			PreparedStatement ps = conn.prepareStatement(sql);
			//文の「?」に使用する値を設定してSQL文を完成
			
			ps.setInt(1,user.getUserId());
			ps.setString(2,user.getPasswd());
			ps.setString(3,user.getUserName());
			ps.setInt(4,user.getIcon());
			
			//INSERT文実行
			int result = ps.executeUpdate();
			if(result != 1) {
				return false;
				
			//SELECT文を取得後、AllayListに格納
			while(rs.next()) {
				System.out.println("wright");
				int userId = rs.getInt("userId");
				String passwd = rs.getString("passwd");
				String userName = rs.getString("userName");
				int icon = rs.getInt("icon");
				user = new User(userId,passwd,userName,icon);
				userList.add(user);
			}
		}catch(SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//userIdとpasswdで1件検索
	public User findIdAndPass(int userId,String passwd) {
		//DataSourceの取得
		InitialContext ic;
		DataSource ds = null;
		
		try {
			ic = new InitialContext();
			
			//DBの場所
			ds = (DataSource)ic.lookup("java:comp/env/jdbc/calendar");
		}catch(NamingException e) {
			e.printStackTrace();
		}
		
		try(Connection con = ds.getConnection()){
			String sql = "SELECT userId, passwd, userName, icon FROM USER WHERE userId = ? AND passwd = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1,userId);
			ps.setString(2,passwd);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				 return new User(
			                rs.getInt("userId"),
			                rs.getString("passwd"),
			                rs.getString("userName"),
			                rs.getInt("icon")
			            );
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//userIdで1件取得
	public User findById(int userId) {
	    InitialContext ic;
	    DataSource ds = null;

	    try {
	        ic = new InitialContext();
	        ds = (DataSource) ic.lookup("java:comp/env/jdbc/calendar");
	    } catch (NamingException e) {
	        e.printStackTrace();
	    }

	    try (Connection conn = ds.getConnection()) {
	        String sql = "SELECT userId, passwd, userName, icon FROM USER WHERE userId = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, userId);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            return new User(
	                rs.getInt("userId"),
	                rs.getString("passwd"),
	                rs.getString("userName"),
	                rs.getInt("icon")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	    
	}
<<<<<<< HEAD
=======
	public List<User> login(User user){
		List<User> userList = new ArrayList<User>();
		//DataSourceの取得
		InitialContext ic;
		DataSource ds = null;
		
		try {
			ic = new InitialContext();
			System.out.println("login.try");
			//DBの場所
			ds = (DataSource)ic.lookup("java:comp/env/jdbc/calendar");
		}catch(NamingException e) {
			System.out.println("login.catch");
			e.printStackTrace();
		}
		
		try(Connection conn = ds.getConnection()) {
			System.out.println("try");
			//SELECT文の準備
			String sql = "SELECT userId, passwd, userName, icon FROM USER WHERE userID = (?) AND PASSWD = (?) ;";
			PreparedStatement ps = conn.prepareStatement(sql);
			//文の「?」に使用する値を設定してSQL文を完成
			ps.setInt(1,user.getUserId());
			ps.setString(2,user.getPasswd());
			//SELECT文を実行し、結果を取得
			ResultSet rs = ps.executeQuery();
			
			//SELECT文を取得後、AllayListに格納
			while(rs.next()) {
				System.out.println("wright");
				int userId = rs.getInt("userId");
				String passwd = rs.getString("passwd");
				String userName = rs.getString("userName");
				int icon = rs.getInt("icon");
				user = new User(userId,passwd,userName,icon);
				userList.add(user);
			}
		}catch(SQLException e) {
<<<<<<< HEAD
>>>>>>> branch 'master' of https://github.com/manntohihi/calendar.git
=======
			System.out.println("catch");
>>>>>>> branch 'master' of https://github.com/manntohihi/calendar.git
			e.printStackTrace();
			return null;
		}
		return userList;
	}
>>>>>>> branch 'master' of https://github.com/manntohihi/calendar.git
}

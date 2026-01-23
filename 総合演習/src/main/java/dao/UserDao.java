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
	public List<User> findAll(){
		List<User> userList = new ArrayList<User>();
		
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
		
		try(Connection conn = ds.getConnection()) {
			//SELECT文の準備
			String sql = "SELECT userId, passwd, userName, icon FROM USER ORDER BY userId DESC;";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//SELECT文を実行し、結果を取得
			ResultSet rs = ps.executeQuery();
			
			//SELECT文を取得後、AllayListに格納
			while(rs.next()) {
				int userId = rs.getInt("userId");
				String passwd = rs.getString("passwd");
				String userName = rs.getString("userName");
				int icon = rs.getInt("icon");
				User user = new User(userId,passwd,userName,icon);
				userList.add(user);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return userList;
	}
	
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
			String sql = "SELECT userId, passwd, userName, icon FROM USER WHERE ID = (?) AND PASSWORD = (?) ;";
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
			System.out.println("catch");
			e.printStackTrace();
			return null;
		}
		return userList;
	}
}

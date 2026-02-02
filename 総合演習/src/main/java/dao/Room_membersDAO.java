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

import model.Room_members;

public class Room_membersDAO {
	public List<Room_members> findAll(){//全部
		 List<Room_members> roomList = new ArrayList<Room_members>();
		 
		InitialContext initCtx;
		DataSource ds =null;
		try {
			initCtx = new InitialContext();
			ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/calendar");//DBの場所へ変更
		}catch(NamingException e) {
			e.printStackTrace();
		}
		
		try (Connection conn = ds.getConnection()){
			//SELECT文を準備
			String sql = "SELECT ID,ROOMID,USERID FROM ROOM_MEMBERS ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SELECT文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			//SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int id = rs.getInt("ID");
				String roomid = rs.getString("ROOMID");
				String userid = rs.getString("USERID");
				Room_members room_members = new Room_members(id,roomid,userid);
				roomList.add(room_members);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return roomList;
	}
	
	public List<Room_members> find(){//必要なものに変更する
		 List<Room_members> roomList = new ArrayList<Room_members>();
		 
		InitialContext initCtx;
		DataSource ds =null;
		try {
			initCtx = new InitialContext();
			ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/calendar");//DBの場所へ変更
		}catch(NamingException e) {
			e.printStackTrace();
		}
		
		try (Connection conn = ds.getConnection()){
			//SELECT文を準備
			String sql = "SELECT ID,ROOMID,USERID FROM ROOM_MEMBERS ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SELECT文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			//SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int id = rs.getInt("ID");
				String roomid = rs.getString("ROOMID");
				String userid = rs.getString("USERID");
				Room_members room_members = new Room_members(id,roomid,userid);
				roomList.add(room_members);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return roomList;
	}
	
	//insert文
	public boolean createMenber(Room_members Room_members) {
 		InitialContext initCtx;
 		DataSource ds = null;
 		try {
 			initCtx = new InitialContext();
 			ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/calendar");//DBの場所へ変更
 		}catch(NamingException e) {
 			e.printStackTrace();
 		}
 		//データベース接続
 		try (Connection conn = ds.getConnection()){
 			//INSERT文の準備
 			String sql = "INSERT INTO ROOM(ROOMID,ROOMNAME,ROOMPASSWD) VALUES(?,?,?)";//変更
 			PreparedStatement pStmt = conn.prepareStatement(sql);
 			//INSERT文の「?」に使用する値を設定してSQL文を完成
 			pStmt.setInt(1,Room_members.getID());
 			pStmt.setString(2,Room_members.getroomID());
 			pStmt.setString(3,Room_members.getuserID());
 			
 			//INSERT文を実行
 			int result = pStmt.executeUpdate();
 			if (result != 1) {
 				return false;
 			}
 		}catch(SQLException e) {
			e.printStackTrace();
			return false;
 		
 		}
 		return true;
 	}
}

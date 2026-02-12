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
			String sql = "SELECT GROUPID,USERID FROM ROOM_MEMBERS ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SELECT文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			//SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int roomid = rs.getInt("GROUPID");
				int userid = rs.getInt("USERID");
				Room_members room_members = new Room_members(roomid,userid);
				roomList.add(room_members);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return roomList;
	}
	
	public List<Room_members> searchByUseridForGroup(int userid){//useridから検索
		 List<Room_members> roomids = new ArrayList<Room_members>();
		 
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
			String sql = "SELECT GROUPID FROM ROOM_MEMBERS WHERE USERID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//INSERT文の「?」に使用する値を設定してSQL文を完成
 			pStmt.setInt(1,userid);
			//SELECT文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			//SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int roomid = rs.getInt("GROUPID");
				Room_members room_members = new Room_members(roomid,userid);
				roomids.add(room_members);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return roomids;
	}
	
	public Room_members searchUserInGroup(int userid,int groupid){//useridから検索
		Room_members room_members = null;
		 
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
			String sql = "SELECT GROUPID FROM ROOM_MEMBERS WHERE USERID = ? AND groupID = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//INSERT文の「?」に使用する値を設定してSQL文を完成
			pStmt.setInt(1,userid);
			pStmt.setInt(2,groupid);
			//SELECT文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			//SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int userId = rs.getInt("userID");
				int roomId = rs.getInt("GROUPID");
				room_members = new Room_members(roomId,userId);

			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return room_members;
	}
	
	public void setUserGroup(int userid , int groupid){//useridから検索
		 List<Room_members> roomids = new ArrayList<Room_members>();
		 
		InitialContext initCtx;
		DataSource ds =null;
		try {
			initCtx = new InitialContext();
			ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/calendar");//DBの場所へ変更
		}catch(NamingException e) {
			e.printStackTrace();
		}
		
		try (Connection conn = ds.getConnection()){
			//INSERT文を準備
			String sql = "INSERT INTO room_members (groupID, userid) VALUES (?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//INSERT文の「?」に使用する値を設定してSQL文を完成
			pStmt.setInt(1,groupid);
			pStmt.setInt(2,userid);
			//INSERT文を実行し、結果票を取得
			int result = pStmt.executeUpdate();
			

		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//insert文
	public boolean createMember(Room_members Room_members) {
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
 			String sql = "INSERT INTO ROOM_MEMBERS(ID,GROUPID,USERID) VALUES(null,?,?)";//変更
 			PreparedStatement pStmt = conn.prepareStatement(sql);
 			//INSERT文の「?」に使用する値を設定してSQL文を完成
 			//pStmt.setString(1,Room_members.getID());
 			pStmt.setInt(1,Room_members.getroomID());
 			pStmt.setInt(2,Room_members.getuserID());
 			
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
	
	public boolean setColor(int groupID,int userID,String color) {
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
 			String sql = "UPDATE room_members SET color = ? WHERE groupID = ? AND userID = ?;";//変更
 			PreparedStatement pStmt = conn.prepareStatement(sql);
 			//INSERT文の「?」に使用する値を設定してSQL文を完成
 			//pStmt.setString(1,Room_members.getID());
 			pStmt.setString(1,color);
 			pStmt.setInt(2,groupID);
 			pStmt.setInt(3,userID);
 			
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

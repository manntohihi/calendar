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

import model.Room;


public class RoomDao {
//SELECT文関係
	 public List<Room> findAll(){//全部
		 List<Room> roomList = new ArrayList<Room>();
		 
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
			String sql = "SELECT ROOMID,ROOMNAME,ROOMPASSWORD FROM ROOM ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SELECT文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			//SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int id = rs.getInt("ID");
				String roomname = rs.getString("ROOMNAME");
				String roompassword = rs.getString("ROOMPASSWORD");
				Room room = new Room(id,roomname,roompassword);
				roomList.add(room);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return roomList;
 	}
	 
	 public List<Room> findFromID(int ID){//部屋IDから取得
		 List<Room> roomList = new ArrayList<Room>();
		 
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
			String sql = "SELECT ROOMID,ROOMNAME,ROOMPASSWORD FROM ROOM WHERE ID = ? ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//INSERT文の「?」に使用する値を設定してSQL文を完成
 			pStmt.setInt(1,ID);
 			
			//SELECT文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			//SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int id = 0 ;
				String roomname = rs.getString("ROOMNAME");
				String roompassword = null;
				Room room = new Room(id,roomname,roompassword);
				roomList.add(room);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return roomList;
 	 }
	 
	 public List<Room> findIDRoomname(Room room){//部屋名
		 List<Room> roomList = new ArrayList<Room>();
		 
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
			String sql = "SELECT ROOMID,ROOMNAME, FROM ROOM WHERE ROOMID = ? ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//INSERT文の「?」に使用する値を設定してSQL文を完成
 			pStmt.setInt(1,room.getId());
 			
			//SELECT文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			//SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int id = rs.getInt("ID");
				String roomid = null;
				String roomname = rs.getString("ROOMNAME");
				String roompassword = null;
				room = new Room(id,roomname,roompassword);
				roomList.add(room);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return roomList;
 	}
	 
	 public List<Room> findIDPass(Room room){//部屋名とパスワード
		 List<Room> roomList = new ArrayList<Room>();
		 
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
			String sql = "SELECT ROOMID,ROOMNAME,ROOMPASSWD FROM ROOM WHERE ROOMID = ? AND ROOMPASSWD = ? ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//INSERT文の「?」に使用する値を設定してSQL文を完成
 			pStmt.setInt(1,room.getId());
 			pStmt.setString(2,room.getRoompassword());
 			
			//SELECT文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			//SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int id = rs.getInt("ROOMID");
				String roomname = rs.getString("ROOMNAME");
				String roompassword = rs.getString("ROOMPASSWD");
				room = new Room(id,roomname,roompassword);
				roomList.add(room);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return roomList;
 	}
	 
 
//INSERT文関係
 	public boolean createRoom(Room room) {
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
 			String sql = "INSERT INTO ROOM(ROOMID,ROOMNAME,ROOMPASSWORRD) VALUES(?,?,?)";//変更
 			PreparedStatement pStmt = conn.prepareStatement(sql);
 			//INSERT文の「?」に使用する値を設定してSQL文を完成
 			pStmt.setInt(1,room.getId());
 			pStmt.setString(2,room.getRoomname());
 			pStmt.setString(3,room.getRoompassword());
 			
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

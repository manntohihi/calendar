package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.CalendarEvent;
import model.CalendraEventList;
import model.barEvent;


public class CalendarDAO {



	public  List<barEvent> findCalendarDate(int loginUserId,int GroupId){
		
		List<barEvent> barEventList = new ArrayList();
		
		InitialContext ic;
		DataSource ds = null;
		// JBDCドライバの読み込みorエラー表示
		try {
			ic = new InitialContext();
			
			//DBの場所
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/calendar");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		
		//データベース接続、connに詰め替え
	    try(Connection conn = ds.getConnection()){

	    	String sql = "SELECT e.*, rm.color "
	    	           + "FROM calendar_events e "
	    	           + "JOIN room_members rm "
	    	           + "ON e.groupID = rm.groupID "
	    	           + "AND e.created_by = rm.userID "
	    	           + "WHERE e.groupID = ?";

	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setLong(1, GroupId);

	        ResultSet rs = ps.executeQuery();

	        while(rs.next()) {

	            barEvent barEvent = new barEvent(
	                rs.getInt("id"),
	                rs.getInt("groupID"),
	                rs.getString("title"),
	                rs.getString("description"),
	                rs.getTimestamp("start_datetime").toLocalDateTime(),
	                rs.getTimestamp("end_datetime").toLocalDateTime(),
	                rs.getInt("created_by"),
	                rs.getString("color"),
	                rs.getString("name")
	            );

	            barEventList.add(barEvent);
	        }

		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return barEventList;
		
		
		
	}
	public  CalendarEvent findBarDate(int loginUserId,int Id){
		
		CalendarEvent CalendarEvent = null;
		List<CalendarEvent> CalendarEventList = new ArrayList();
		
		long start;
		long end;
		start = System.currentTimeMillis();
		
		InitialContext ic;
		DataSource ds = null;
		// JBDCドライバの読み込みorエラー表示
		try {
			ic = new InitialContext();
			
			//DBの場所
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/calendar");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		//データベース接続、connに詰め替え
		try(Connection conn = ds.getConnection()){
		
		//SELCT文を準備
		String sql = "SELECT e.* "
				+ "FROM calendar_events e "
				+ "JOIN room_members rm ON e.groupID = rm.groupID "
				+ "WHERE rm.userID = ? " 
				+ "AND e.id =  ? ;";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setLong(1, loginUserId);
		ps.setLong(2, Id);
		
		
		//SELECT文を実行し、結果表を取得
		ResultSet rs = ps.executeQuery();
		
		
		
		//結果表に格納された内容を
		//Employeeインスタンスに設定し、ArrayListインスタンスに追加
		while(rs.next()) {
			int id = rs.getInt("id");
			int group_id = rs.getInt("groupID");
			String title = rs.getString("title");
			String description = rs.getString("description");
			LocalDateTime start_datetime = rs.getTimestamp("start_datetime").toLocalDateTime();
			LocalDateTime end_datetime = rs.getTimestamp("end_datetime").toLocalDateTime();
			int created_by = rs.getInt("created_by");
			String name = rs.getString("name");
			
			CalendarEvent = new CalendarEvent(id, group_id, title, description,start_datetime
					, end_datetime, created_by, name);

		}
		end = System.currentTimeMillis();

		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return CalendarEvent;
		
		
		
	}
	
	public List<barEvent> findUserCalendarDate(int loginUserId,int GroupId){

	    List<barEvent> barEventList = new ArrayList<>();
	    
	    
	    
		InitialContext ic;
		DataSource ds = null;
		// JBDCドライバの読み込みorエラー表示
		try {
			ic = new InitialContext();
			
			//DBの場所
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/calendar");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		
		//データベース接続、connに詰め替え
	    try(Connection conn = ds.getConnection()){

	        String sql = "SELECT e.*, rm.color "
	                   + "FROM calendar_events e "
	                   + "JOIN room_members rm ON e.groupID = rm.groupID "
	                   + "WHERE rm.userID = ? "
	                   + "AND e.groupID = ? "
	                   + "AND e.created_by = ?";

	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setLong(1, loginUserId);
	        ps.setLong(2, GroupId);
	        ps.setLong(3, loginUserId);

	        ResultSet rs = ps.executeQuery();

	        while(rs.next()) {

	            barEvent barEvent = new barEvent(
	                rs.getInt("id"),
	                rs.getInt("groupID"),
	                rs.getString("title"),
	                rs.getString("description"),
	                rs.getTimestamp("start_datetime").toLocalDateTime(),
	                rs.getTimestamp("end_datetime").toLocalDateTime(),
	                rs.getInt("created_by"),
	                rs.getString("color"),
	                rs.getString("name")
	            );

	            barEventList.add(barEvent);
	        }

		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return barEventList;
		
		
		
	}
	



	public void setCalendarDate(int group_id,String title,String memo, LocalDateTime start_datetime,LocalDateTime end_datetime,int created_by, String name){
		CalendraEventList PostPrductLogic = null;
		List<CalendarEvent> CalendarEventList = new ArrayList();
		
		
		
		InitialContext ic;
		DataSource ds = null;
		 // JBDCドライバの読み込みorエラー表示
		// JBDCドライバの読み込みorエラー表示
		try {
			ic = new InitialContext();
			
			//DBの場所
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/calendar");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		//データベース接続、connに詰め替え
		try(Connection conn = ds.getConnection()){
		
		//INSERT文を準備
			String sql =
					"INSERT INTO calendar_events " +
					"(groupID, title, description, start_datetime, end_datetime, created_by, name) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setLong(1, group_id);
				ps.setString(2, title);
				ps.setString(3, memo);
				ps.setTimestamp(4, Timestamp.valueOf(start_datetime));
				ps.setTimestamp(5, Timestamp.valueOf(end_datetime));
				ps.setLong(6, created_by);
				ps.setString(7, name);
				System.out.println(ps);
				ps.executeUpdate();
		
		}catch(SQLException e){
			e.printStackTrace();

		}
	}
		public void deleteCalendarDate(int id,int created_by){
			CalendraEventList PostPrductLogic = null;
			List<CalendarEvent> CalendarEventList = new ArrayList();
			
			
			
			InitialContext ic;
			DataSource ds = null;
			 // JBDCドライバの読み込みorエラー表示
			// JBDCドライバの読み込みorエラー表示
			try {
				ic = new InitialContext();
				
				//DBの場所
				ds = (DataSource) ic.lookup("java:comp/env/jdbc/calendar");
			} catch (NamingException e) {
				e.printStackTrace();
			}
			//データベース接続、connに詰め替え
			try(Connection conn = ds.getConnection()){
			
			//SELCT文を準備
					String sql = 
					"DELETE FROM calendar_events where id = ? AND created_by = ?;";
					
					PreparedStatement ps = conn.prepareStatement(sql);
					
					ps.setLong(1, id);
					ps.setLong(1, created_by);
					ps.executeUpdate();
			
			}catch(SQLException e){
				e.printStackTrace();

			}
		
		
		
		
	}
		
		public void updateCalendarDate(int group_id,String title,String description, LocalDateTime start,LocalDateTime end,int created_by, String name){
			CalendraEventList PostPrductLogic = null;
			List<CalendarEvent> CalendarEventList = new ArrayList();
			
			
			
			InitialContext ic;
			DataSource ds = null;
			 // JBDCドライバの読み込みorエラー表示
			// JBDCドライバの読み込みorエラー表示
			try {
				ic = new InitialContext();
				
				//DBの場所
				ds = (DataSource) ic.lookup("java:comp/env/jdbc/calendar");
			} catch (NamingException e) {
				e.printStackTrace();
			}
			//データベース接続、connに詰め替え
			try (Connection conn = ds.getConnection()) {

	            String sql =
	                "UPDATE calendar_events " +
	                "SET title = ?, " +
	                "    description = ?, " +
	                "    start_datetime = ?, " +
	                "    end_datetime = ?, " +
	                "    name = ? " +
	                "WHERE id = ?";

	            PreparedStatement ps =
	                conn.prepareStatement(sql);

	            ps.setString(1, title);
	            ps.setString(2, description);
	            ps.setTimestamp(3, Timestamp.valueOf(start));
	            ps.setTimestamp(4, Timestamp.valueOf(end));
	            ps.setString(5, name);
	            ps.setLong(6, group_id);

	            System.out.println(ps);
	            ps.executeUpdate();
	        

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
		public List<String> findColor(int ID,int userid){
		    List<String> colorList = new ArrayList<>();
		    
		    InitialContext ic;
			DataSource ds = null;
			 // JBDCドライバの読み込みorエラー表示
			// JBDCドライバの読み込みorエラー表示
			try {
				ic = new InitialContext();
				
				//DBの場所
				ds = (DataSource) ic.lookup("java:comp/env/jdbc/calendar");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		    try(Connection conn = ds.getConnection()){
		        String sql ="SELECT color FROM room_members "
		                  + "WHERE groupID = ? AND color IS NOT NULL AND NOT userID = ?";

		        PreparedStatement ps = conn.prepareStatement(sql);
		        ps.setLong(1, ID);
		        ps.setLong(2, userid);

		        ResultSet rs = ps.executeQuery();

		        while(rs.next()) {
		            colorList.add(rs.getString("color"));
		        }

		    }catch(SQLException e){
		        e.printStackTrace();
		    }

		    return colorList; // nullは返さない
		}

	
	
	
}

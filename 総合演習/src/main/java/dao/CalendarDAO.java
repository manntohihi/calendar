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


public class CalendarDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost/calendar";	
	private final String JDBC_USER = "root";
	private final String JDBC_PASS = "kcsf";


	public  List<CalendarEvent> findCalendarDate(int loginUserId,int GroupId){
		
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
				+ "JOIN group_members gm ON e.group_id = gm.group_id "
				+ "WHERE gm.user_id = ? " 
				+ "AND e.group_id =  ? ;";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setLong(1, loginUserId);
		ps.setLong(2, GroupId);
		
		
		//SELECT文を実行し、結果表を取得
		ResultSet rs = ps.executeQuery();
		
		//結果表に格納された内容を
		//Employeeインスタンスに設定し、ArrayListインスタンスに追加
		while(rs.next()) {
			int id = rs.getInt("id");
			int group_id = rs.getInt("group_id");
			String title = rs.getString("title");
			String description = rs.getString("description");
			LocalDateTime start_datetime = rs.getTimestamp("start_datetime").toLocalDateTime();
			LocalDateTime end_datetime = rs.getTimestamp("end_datetime").toLocalDateTime();
			int created_by = rs.getInt("created_by");
			String coller = rs.getString("coller");
			
			CalendarEvent = new CalendarEvent(id, group_id, title, description,start_datetime, end_datetime, created_by,coller
					);
			CalendarEventList.add(CalendarEvent);
		}
		end = System.currentTimeMillis();

		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return CalendarEventList;
		
		
		
	}



	public void setCalendarDate(int group_id,String title,String texdescription, LocalDateTime start_datetime,LocalDateTime end_datetime,int created_by){
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
				"INSERT INTO calendar_events " +
				"(group_id, title, description, start_datetime, end_datetime, created_by) " +
				"VALUES (?, ?, ?, ?, ?, ?)";

				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setLong(1, group_id);
				ps.setString(2, title);
				ps.setString(3, texdescription);
				ps.setTimestamp(4, Timestamp.valueOf(start_datetime));
				ps.setTimestamp(5, Timestamp.valueOf(end_datetime));
				ps.setLong(6, created_by);
				System.out.println(ps);
				ps.executeUpdate();
		
		}catch(SQLException e){
			e.printStackTrace();

		}
		
		
		
		
	}
	
	
	
}

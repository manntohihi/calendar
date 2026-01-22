package model;

import java.time.LocalDateTime;

public class CalendarEvent {

	int id; 
	int group_id;
	String title;
	String texdescription;
	LocalDateTime start_datetime;
	LocalDateTime end_datetime;
	int created_by;

	//コンストラクタ
	public CalendarEvent() {
		
	}
	
	public CalendarEvent(int id,int group_id,String title,String texdescription,LocalDateTime start_datetime,LocalDateTime end_datetime,int created_by) {
		this.id = id;
		this.group_id = group_id;
		this.title = title;
		this.texdescription = texdescription;
		this.start_datetime = start_datetime;
		this.end_datetime = end_datetime;
		this.created_by = created_by;
	}
	

	
	public int getId() {
		return this.id;
	}
	
	public int getPasswd() {
		return this.group_id;
	}
	
	public String getUserName() {
		return this.title;
	}
	
	public String getTexdescription() {
		return this.texdescription;
	}
	
	public LocalDateTime getStart_datetime() {
		return this.start_datetime;
	}
	
	public LocalDateTime getEnd_datetime() {
		return this.end_datetime;
	}
	
	public int getCreated_by() {
		return this.created_by;
	}
	
}


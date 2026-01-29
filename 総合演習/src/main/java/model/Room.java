package model;

public class Room {
	int id = 0;
	String roomname = null;
	String roompassword =null;
	public Room() {
		
	}
	public Room(int id,String roomname, String roompassword) {
		this.id = id;
		this.roomname = roomname;
		this.roompassword = roompassword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRoomname() {
		return roomname;
	}
	
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	
	public String getRoompassword() {
		return roompassword;
	}
	
	public void setRoompassword(String roompassword) {
		this.roompassword = roompassword;
	}
}

package model;

public class Room_members {
	int ID;
	String roomID = null;
	String userID = null;
	
	public Room_members(int id,String roomid,String userid){
		this.ID = id;
		this.roomID = roomid;
		this.userID = userid;
	}
	
	public int getID() {
		return this.ID;
	}
	public void setID(int id) {
		this.ID=id;
	}
	public String getroomID() {
		return this.roomID;
	}
	public void setroomID(String roomid) {
		this.roomID=roomid;
	}
	public String getuserID() {
		return this.userID;
	}
	public void setID(String userid) {
		this.userID=userid;
	}
	
}

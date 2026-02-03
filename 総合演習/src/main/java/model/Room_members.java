package model;

public class Room_members {
	String ID=null;
	int roomID;//=groupid
	int userID;
	
	public Room_members(){
	}
	
	public Room_members(String id,int roomid,int userid){
		this.ID = id;
		this.roomID = roomid;
		this.userID = userid;
	}
	
	public String getID() {
		return this.ID;
	}
	public void setID(String id) {
		this.ID=id;
	}
	public int getroomID() {
		return this.roomID;
	}
	public void setroomID(int roomid) {
		this.roomID=roomid;
	}
	public int getuserID() {
		return this.userID;
	}
	public void setID(int userid) {
		this.userID=userid;
	}
	
}

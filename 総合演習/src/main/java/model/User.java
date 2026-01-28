package model;

public class User {
	//USERテーブル用のローカル変数
	private int userId;
	private String passwd;
	private String userName = null;
	private int icon;
	
	//コンストラクタ
	public User() {
		
	}
	
	public User(int userId,String passwd,String userName,int icon) {
		this.userId = userId;
		this.passwd = passwd;
		this.userName = userName;
		this.icon = icon;
		
	}
	
	//userIdのsetter&getter
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	//passwdのsetter&getter
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getPasswd() {
		return passwd;
	}
	
	//userNameのsetter&getter
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	//iconのsetter&getter
	public void setIcon(int icon) {
		this.icon = icon;
	}
	
	public int getIcon() {
		return icon;
	}
}

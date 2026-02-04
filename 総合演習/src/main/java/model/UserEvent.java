package model;

public class UserEvent {
	//USERテーブル用のローカル変
	private String title;
	private long remainingDays;
	
	//コンストラクタ
	public UserEvent(String title,long remainingDays) {
		this.title = title;
		this.remainingDays = remainingDays;
	}
	
	//全てのコンストラクタ
	public UserEvent() {
		
	}
	
	//titleのsetter&getter
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setRemainingDays(long remainingDays) {
		this.remainingDays = remainingDays;
	}
	
	public long getRemainingDays() {
		return this.remainingDays;
	}
}


package noteApp.config.bean;

public class Note {
	String username;
	String note_name;
	String note_content;
	String note_id;
	String date_of_creation;
	
	
	public Note(String username,String note_name,String note_content,String note_id,String date_of_creation){
		this.username = username;
		this.note_name = note_name;
		this.note_content = note_content;
		this.note_id = note_id;
		this.date_of_creation = date_of_creation;
		
	}
	
	public Note() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNote_name() {
		return note_name;
	}

	public void setNote_name(String note_name) {
		this.note_name = note_name;
	}

	public String getNote_content() {
		return note_content;
	}

	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}

	public String getNote_id() {
		return note_id;
	}

	public void setNote_id(String note_id) {
		this.note_id = note_id;
	}

	public String getDate_of_creation() {
		return date_of_creation;
	}

	public void setDate_of_creation(String date_of_creation) {
		this.date_of_creation = date_of_creation;
	}
	
	public String toString() {
		return this.username+","+
				this.note_name+"," +
				this.note_content+","+
				this.note_id+","+
				this.date_of_creation ;
		
	}
}

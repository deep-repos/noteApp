package noteApp.config.bean;

public class Message {

	boolean isValid;
	String message;
	
	public Message() {}
	
	public Message(boolean isValid,String message) {
		this.isValid = isValid;
		this.message = message;
		
	}
	
	public String toString(){
		return this.isValid +"," + this.message;
		
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

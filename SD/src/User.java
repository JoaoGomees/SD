
public class User { 

	private String email;
	private String password;
	
	public User (String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String get_email () {
		return this.email;
	}
	
	public String get_password () {
		return this.password;
	}
	
}

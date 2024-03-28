package finonline.be.domain.request;

public class UserAuthRequest {
	
	private String name;
	private String pass;
	
	UserAuthRequest() {}
	
	UserAuthRequest(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getPass() {
		return this.pass;
	}
}

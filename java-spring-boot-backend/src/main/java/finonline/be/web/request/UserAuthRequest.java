package finonline.be.web.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserAuthRequest {
	
	private String name;
	private String pass;
	
	UserAuthRequest() {}
	
	public UserAuthRequest(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}
}

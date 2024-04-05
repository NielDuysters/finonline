package finonline.be.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthRequest {
	
	private String name;
	private String pass;
	
	UserAuthRequest() {}
	
	UserAuthRequest(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}
}

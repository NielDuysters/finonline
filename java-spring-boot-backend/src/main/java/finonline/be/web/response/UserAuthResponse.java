package finonline.be.web.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserAuthResponse {
	
	private Integer id;
	private String name;
	private String token;
	
	public UserAuthResponse() {}
	
	public UserAuthResponse(Integer id, String name, String token) {
		this.id = id;
		this.name = name;
		this.token = token;
	}
}

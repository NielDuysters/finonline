package finonline.be.domain.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthResponse {
	
	private Integer id;
	private String name;
	private String token;
	
	UserAuthResponse() {}
	
	public UserAuthResponse(Integer id, String name, String token) {
		this.id = id;
		this.name = name;
		this.token = token;
	}
}

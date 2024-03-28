package finonline.be.domain.model.response;

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

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return this.token;
	}
}

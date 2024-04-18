package finonline.be.web.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchUser {
	
	private BigDecimal newStartCapital;

	public PatchUser() {}
	
	public PatchUser(BigDecimal newStartCapital) {
		this.newStartCapital = newStartCapital;
	}
}

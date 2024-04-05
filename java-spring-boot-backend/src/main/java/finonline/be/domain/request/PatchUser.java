package finonline.be.domain.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchUser {
	
	private BigDecimal newStartCapital;

	PatchUser() {}
	
	PatchUser(BigDecimal newStartCapital) {
		this.newStartCapital = newStartCapital;
	}
}

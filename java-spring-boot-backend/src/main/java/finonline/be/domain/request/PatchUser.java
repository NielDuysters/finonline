package finonline.be.domain.request;

import java.math.BigDecimal;

public class PatchUser {
	
	private BigDecimal newStartCapital;

	PatchUser() {}
	
	PatchUser(BigDecimal newStartCapital) {
		this.newStartCapital = newStartCapital;
	}
	
	public BigDecimal getNewStartCapital() {
		return newStartCapital;
	}

	public void setNewStartCapital(BigDecimal newStartCapital) {
		this.newStartCapital = newStartCapital;
	}
}

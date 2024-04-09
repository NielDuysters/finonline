package finonline.be.web.request;

import finonline.be.domain.types.CashflowType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCashflowCategory {
	private String label;
	
	@Enumerated(EnumType.STRING)
	private CashflowType type;
	
	AddCashflowCategory() {}
	
	AddCashflowCategory(String label, CashflowType type) {
		this.label = label;
		this.type = type;
	}
}

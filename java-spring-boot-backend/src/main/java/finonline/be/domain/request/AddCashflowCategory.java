package finonline.be.domain.request;

import finonline.be.domain.types.CashflowType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class AddCashflowCategory {
	private String label;
	
	@Enumerated(EnumType.STRING)
	private CashflowType type;
	
	AddCashflowCategory() {}
	
	AddCashflowCategory(String label, CashflowType type) {
		this.label = label;
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public CashflowType getType() {
		return type;
	}

	public void setType(CashflowType type) {
		this.type = type;
	}
	
	
}

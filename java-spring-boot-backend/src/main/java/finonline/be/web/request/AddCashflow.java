package finonline.be.web.request;

import java.math.BigDecimal;

import finonline.be.domain.types.CashflowType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCashflow {
	@Enumerated(EnumType.STRING)
	private CashflowType type;
	
	private Integer categoryId;
	private BigDecimal amount;
	private String description;
	private String transactionDate;

	AddCashflow() {}
	
	AddCashflow(CashflowType type, Integer categoryId, BigDecimal amount, String description, String transactionDate) {
		this.type = type;
		this.categoryId = categoryId;
		this.amount = amount;
		this.description = description;
		this.transactionDate = transactionDate;
	}
}

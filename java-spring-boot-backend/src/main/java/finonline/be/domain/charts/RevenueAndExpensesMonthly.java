package finonline.be.domain.charts;

import java.math.BigDecimal;

import finonline.be.domain.types.CashflowType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class RevenueAndExpensesMonthly {
	
	private BigDecimal amount;
	
	private String dateYear;

	@Enumerated(EnumType.STRING)
	private CashflowType type;
	
	RevenueAndExpensesMonthly() {}
	
	RevenueAndExpensesMonthly(BigDecimal amount, String dateYear, CashflowType type) {
		this.amount = amount;
		this.dateYear = dateYear;
		this.type = type;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDateYear() {
		return dateYear;
	}

	public void setDateYear(String dateYear) {
		this.dateYear = dateYear;
	}

	public CashflowType getType() {
		return type;
	}

	public void setType(CashflowType type) {
		this.type = type;
	}
}

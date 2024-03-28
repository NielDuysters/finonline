package finonline.be.domain.charts;

import java.math.BigDecimal;

import finonline.be.domain.model.CashflowCategory;
import finonline.be.domain.types.CashflowType;
import jakarta.persistence.*;

public class TransactionsPerCategory {
	
	private BigDecimal amount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private CashflowCategory cashflowCategory;

	@Enumerated(EnumType.STRING)
	private CashflowType type;
	
	TransactionsPerCategory() {}
	
	TransactionsPerCategory(BigDecimal amount, CashflowCategory cashflowCategory, CashflowType type) {
		this.amount = amount;
		this.cashflowCategory = cashflowCategory;
		this.type = type;
	}
	 
	
	public CashflowCategory getCashflowCategory() {
		return cashflowCategory;
	}

	public void setCashflowCategory(CashflowCategory cashflowCategory) {
		this.cashflowCategory = cashflowCategory;
	}

	public CashflowType getType() {
		return type;
	}

	public void setType(CashflowType type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}

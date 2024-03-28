package finonline.be.domain.request;

import java.math.BigDecimal;

import finonline.be.domain.types.CashflowType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

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

	public CashflowType getType() {
		return type;
	}

	public void setType(CashflowType type) {
		this.type = type;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
}

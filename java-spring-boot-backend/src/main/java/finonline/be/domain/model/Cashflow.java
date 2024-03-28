package finonline.be.domain.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import finonline.be.auth.IOwner;
import finonline.be.auth.OwnershipListener;
import finonline.be.domain.types.CashflowType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "cashflow")
@EntityListeners(OwnershipListener.class)
public class Cashflow implements IOwner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cashflow_id_seq")
	@SequenceGenerator(name = "cashflow_id_seq", sequenceName = "cashflow_id_seq", allocationSize = 1)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private CashflowType type;
	
	private BigDecimal amount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private CashflowCategory cashflowCategory;
	
	private String description;
	
	@Column(name = "transaction_date")
	private Date transactionDate;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	public Cashflow() {}
	
	Cashflow(CashflowType type, CashflowCategory cashflowCategory, BigDecimal amount, String description, Date transactionDate) {
		this.type = type;
		this.cashflowCategory = cashflowCategory;
		this.amount = amount;
		this.description = description;
		this.transactionDate = transactionDate;
	}
	
	public Integer getId() {
		return id;
	}

	public CashflowType getType() {
		return type;
	}

	public void setType(CashflowType type) {
		this.type = type;
	}

	public CashflowCategory getCashflowCategory() {
		return cashflowCategory;
	}

	public void setCashflowCategory(CashflowCategory cashflowCategory) {
		this.cashflowCategory = cashflowCategory;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getTransactionDate() {
		return this.transactionDate;
	}
	
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	@JsonIgnore
	public User getOwner() {
		return this.user;
	}

}

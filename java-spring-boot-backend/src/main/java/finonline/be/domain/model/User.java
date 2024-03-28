package finonline.be.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import finonline.be.domain.types.CashflowType;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
	@SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
	private Integer id;
	
	@Column(unique=true)
	private String name;
	
	private String pass;
	
	@Column(name="start_capital")
	private BigDecimal startCapital;
	

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	Collection<CashflowCategory> cashflowCategories = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Cashflow> cashflows = new ArrayList<>();
	
	User() {}
	
	User(String name, String pass) {
		this.name = name;
		
		String hash = BCrypt.hashpw(pass, BCrypt.gensalt());
		this.pass = hash;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	@JsonProperty
	public void setPass(String pass) {
		String hash = BCrypt.hashpw(pass, BCrypt.gensalt());
		this.pass = hash;
	}
	
	@JsonIgnore
	public String getPass() {
		return this.pass;
	}
	
	public BigDecimal getStartCapital() {
		return startCapital;
	}

	public void setStartCapital(BigDecimal startCapital) {
		this.startCapital = startCapital;
	}
	
	@Transient
	public BigDecimal getCurrentCapital() {
		
		BigDecimal currentCapital = this.startCapital;
		for (Cashflow c : this.cashflows) {
			if (c.getType() == CashflowType.REVENUE) {
				currentCapital = currentCapital.add(c.getAmount());
			} else {
				currentCapital = currentCapital.subtract(c.getAmount());
			}
		}
		
		return currentCapital;
	}
}

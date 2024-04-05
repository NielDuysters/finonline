package finonline.be.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import finonline.be.auth.IOwner;
import finonline.be.auth.OwnershipListener;
import finonline.be.domain.types.CashflowType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.EnumType;

import java.util.Random;

@Entity
@Table(name = "cashflow_categories")
@EntityListeners(OwnershipListener.class)
@Getter
@Setter
public class CashflowCategory implements IOwner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cashflow_categories_id_seq")
	@SequenceGenerator(name = "cashflow_categories_id_seq", sequenceName = "cashflow_categories_id_seq", allocationSize = 1)
	private Integer id;
	
	@Column(unique=true)
	private String label;
	private String color;

	@Enumerated(EnumType.STRING)
	private CashflowType type;
	
	private boolean persistent;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	

	public CashflowCategory() {}
	
	public CashflowCategory(String label, String color, CashflowType type, boolean persistent, User user) {
		this.label = label;
		this.color = color;
		this.type = type;
		this.persistent = persistent;
		this.user = user;
	}
	
	public void generateRandomColor() {
		Random random = new Random();
		
		int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        String hexRed = Integer.toHexString(red);
        String hexGreen = Integer.toHexString(green);
        String hexBlue = Integer.toHexString(blue);

        hexRed = padZero(hexRed);
        hexGreen = padZero(hexGreen);
        hexBlue = padZero(hexBlue);

        // Concatenate the components to form the hexadecimal color code
        String hexColor = "#" + hexRed + hexGreen + hexBlue;
        this.color = hexColor;
	}
	
	private String padZero(String hex) {
        return hex.length() == 1 ? "0" + hex : hex;
    }

	@Override
	@JsonIgnore
	public User getOwner() {
		return this.user;
	}
}


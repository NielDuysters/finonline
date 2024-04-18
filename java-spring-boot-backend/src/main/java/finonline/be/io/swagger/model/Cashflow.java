package finonline.be.io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import finonline.be.io.swagger.model.CashflowCategory;
import finonline.be.io.swagger.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Cashflow
 */
@Validated
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-04-16T04:12:32.032577+02:00[Europe/Brussels]")


public class Cashflow   {
  @JsonProperty("id")
  private Integer id = null;

  /**
   * Gets or Sets type
   */
  public enum TypeEnum {
    REVENUE("REVENUE"),
    
    EXPENSE("EXPENSE");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("amount")
  private BigDecimal amount = null;

  @JsonProperty("cashflowCategory")
  private CashflowCategory cashflowCategory = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("transactionDate")
  private String transactionDate = null;

  @JsonProperty("user")
  private User user = null;

  public Cashflow id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(example = "123", description = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Cashflow type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   **/
  @Schema(example = "EXPENSE", description = "")
  
    public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public Cashflow amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
   **/
  @Schema(example = "25.5", description = "")
  
    @Valid
    public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Cashflow cashflowCategory(CashflowCategory cashflowCategory) {
    this.cashflowCategory = cashflowCategory;
    return this;
  }

  /**
   * Get cashflowCategory
   * @return cashflowCategory
   **/
  @Schema(description = "")
  
    @Valid
    public CashflowCategory getCashflowCategory() {
    return cashflowCategory;
  }

  public void setCashflowCategory(CashflowCategory cashflowCategory) {
    this.cashflowCategory = cashflowCategory;
  }

  public Cashflow description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(example = "Bought food.", description = "")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Cashflow transactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
    return this;
  }

  /**
   * Get transactionDate
   * @return transactionDate
   **/
  @Schema(example = "31/03/2024", description = "")
  
    public String getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }

  public Cashflow user(User user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
   **/
  @Schema(description = "")
  
    @Valid
    public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cashflow cashflow = (Cashflow) o;
    return Objects.equals(this.id, cashflow.id) &&
        Objects.equals(this.type, cashflow.type) &&
        Objects.equals(this.amount, cashflow.amount) &&
        Objects.equals(this.cashflowCategory, cashflow.cashflowCategory) &&
        Objects.equals(this.description, cashflow.description) &&
        Objects.equals(this.transactionDate, cashflow.transactionDate) &&
        Objects.equals(this.user, cashflow.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, amount, cashflowCategory, description, transactionDate, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Cashflow {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    cashflowCategory: ").append(toIndentedString(cashflowCategory)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    transactionDate: ").append(toIndentedString(transactionDate)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

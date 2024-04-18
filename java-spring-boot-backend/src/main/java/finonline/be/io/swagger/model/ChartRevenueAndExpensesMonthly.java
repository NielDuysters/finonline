package finonline.be.io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ChartRevenueAndExpensesMonthly
 */
@Validated
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-04-05T01:57:44.169955+02:00[Europe/Brussels]")


public class ChartRevenueAndExpensesMonthly   {
  @JsonProperty("amount")
  private BigDecimal amount = null;

  @JsonProperty("dateYear")
  private String dateYear = null;

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

  public ChartRevenueAndExpensesMonthly amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
   **/
  @Schema(example = "546.64", description = "")
  
    @Valid
    public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public ChartRevenueAndExpensesMonthly dateYear(String dateYear) {
    this.dateYear = dateYear;
    return this;
  }

  /**
   * Get dateYear
   * @return dateYear
   **/
  @Schema(example = "April 2024", description = "")
  
    public String getDateYear() {
    return dateYear;
  }

  public void setDateYear(String dateYear) {
    this.dateYear = dateYear;
  }

  public ChartRevenueAndExpensesMonthly type(TypeEnum type) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChartRevenueAndExpensesMonthly chartRevenueAndExpensesMonthly = (ChartRevenueAndExpensesMonthly) o;
    return Objects.equals(this.amount, chartRevenueAndExpensesMonthly.amount) &&
        Objects.equals(this.dateYear, chartRevenueAndExpensesMonthly.dateYear) &&
        Objects.equals(this.type, chartRevenueAndExpensesMonthly.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, dateYear, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChartRevenueAndExpensesMonthly {\n");
    
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    dateYear: ").append(toIndentedString(dateYear)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

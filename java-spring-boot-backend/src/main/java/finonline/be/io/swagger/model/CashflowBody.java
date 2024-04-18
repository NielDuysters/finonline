package finonline.be.io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CashflowBody
 */
@Validated
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-04-16T03:36:49.368968+02:00[Europe/Brussels]")


public class CashflowBody   {
  @JsonProperty("label")
  private String label = null;

  /**
   * Gets or Sets cashflowType
   */
  public enum CashflowTypeEnum {
    REVENUE("REVENUE"),
    
    EXPENSE("EXPENSE");

    private String value;

    CashflowTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CashflowTypeEnum fromValue(String text) {
      for (CashflowTypeEnum b : CashflowTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("cashflowType")
  private CashflowTypeEnum cashflowType = null;

  public CashflowBody label(String label) {
    this.label = label;
    return this;
  }

  /**
   * Get label
   * @return label
   **/
  @Schema(example = "Food", description = "")
  
    public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public CashflowBody cashflowType(CashflowTypeEnum cashflowType) {
    this.cashflowType = cashflowType;
    return this;
  }

  /**
   * Get cashflowType
   * @return cashflowType
   **/
  @Schema(example = "EXPENSE", description = "")
  
    public CashflowTypeEnum getCashflowType() {
    return cashflowType;
  }

  public void setCashflowType(CashflowTypeEnum cashflowType) {
    this.cashflowType = cashflowType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CashflowBody cashflowBody = (CashflowBody) o;
    return Objects.equals(this.label, cashflowBody.label) &&
        Objects.equals(this.cashflowType, cashflowBody.cashflowType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(label, cashflowType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CashflowBody {\n");
    
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    cashflowType: ").append(toIndentedString(cashflowType)).append("\n");
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

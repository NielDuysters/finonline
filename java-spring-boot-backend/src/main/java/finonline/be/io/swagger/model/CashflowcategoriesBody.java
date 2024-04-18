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
 * CashflowcategoriesBody
 */
@Validated
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-04-05T01:57:44.169955+02:00[Europe/Brussels]")


public class CashflowcategoriesBody   {
  @JsonProperty("label")
  private String label = null;

  /**
   * Gets or Sets type
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
  @JsonProperty("type")
  private CashflowTypeEnum type = null;

  public CashflowcategoriesBody label(String label) {
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

  public CashflowcategoriesBody cashflowType(CashflowTypeEnum cashflowType) {
    this.type = cashflowType;
    return this;
  }

  /**
   * Get cashflowType
   * @return cashflowType
   **/
  @Schema(example = "EXPENSE", description = "")
  
    public CashflowTypeEnum getType() {
    return type;
  }

  public void setType(CashflowTypeEnum cashflowType) {
    this.type = cashflowType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CashflowcategoriesBody cashflowcategoriesBody = (CashflowcategoriesBody) o;
    return Objects.equals(this.label, cashflowcategoriesBody.label) &&
        Objects.equals(this.type, cashflowcategoriesBody.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(label, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CashflowcategoriesBody {\n");
    
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
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

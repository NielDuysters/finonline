package finonline.be.io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import finonline.be.io.swagger.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CashflowCategory
 */
@Validated
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-04-05T01:57:44.169955+02:00[Europe/Brussels]")


public class CashflowCategory   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("label")
  private String label = null;

  @JsonProperty("color")
  private String color = null;

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
  @JsonProperty("type")
  private CashflowTypeEnum type = null;

  @JsonProperty("persistent")
  private Boolean persistent = null;

  @JsonProperty("user")
  private User user = null;

  public CashflowCategory id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(example = "34", description = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public CashflowCategory label(String label) {
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

  public CashflowCategory color(String color) {
    this.color = color;
    return this;
  }

  /**
   * Get color
   * @return color
   **/
  @Schema(description = "")
  
    public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public CashflowCategory type(CashflowTypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get cashflowType
   * @return cashflowType
   **/
  @Schema(example = "EXPENSE", description = "")
  
    public CashflowTypeEnum getCashflowType() {
    return type;
  }

  public void setCashflowType(CashflowTypeEnum cashflowType) {
    this.type = cashflowType;
  }

  public CashflowCategory persistent(Boolean persistent) {
    this.persistent = persistent;
    return this;
  }

  /**
   * Get persistent
   * @return persistent
   **/
  @Schema(example = "false", description = "")
  
    public Boolean isPersistent() {
    return persistent;
  }

  public void setPersistent(Boolean persistent) {
    this.persistent = persistent;
  }

  public CashflowCategory user(User user) {
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
    CashflowCategory cashflowCategory = (CashflowCategory) o;
    return Objects.equals(this.id, cashflowCategory.id) &&
        Objects.equals(this.label, cashflowCategory.label) &&
        Objects.equals(this.color, cashflowCategory.color) &&
        Objects.equals(this.type, cashflowCategory.type) &&
        Objects.equals(this.persistent, cashflowCategory.persistent) &&
        Objects.equals(this.user, cashflowCategory.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, label, color, type, persistent, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CashflowCategory {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    persistent: ").append(toIndentedString(persistent)).append("\n");
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

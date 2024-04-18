package finonline.be.io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */
@Validated
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-04-05T01:57:44.169955+02:00[Europe/Brussels]")


public class User   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("startCapital")
  private BigDecimal startCapital = null;

  @JsonProperty("currentCapital")
  private BigDecimal currentCapital = null;

  public User id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(example = "10", description = "")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(example = "niel", description = "")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User startCapital(BigDecimal startCapital) {
    this.startCapital = startCapital;
    return this;
  }

  /**
   * Get startCapital
   * @return startCapital
   **/
  @Schema(example = "10000.0", description = "")
  
    @Valid
    public BigDecimal getStartCapital() {
    return startCapital;
  }

  public void setStartCapital(BigDecimal startCapital) {
    this.startCapital = startCapital;
  }

  public User currentCapital(BigDecimal currentCapital) {
    this.currentCapital = currentCapital;
    return this;
  }

  /**
   * Get currentCapital
   * @return currentCapital
   **/
  @Schema(example = "12500.0", description = "")
  
    @Valid
    public BigDecimal getCurrentCapital() {
    return currentCapital;
  }

  public void setCurrentCapital(BigDecimal currentCapital) {
    this.currentCapital = currentCapital;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.name, user.name) &&
        Objects.equals(this.startCapital, user.startCapital) &&
        Objects.equals(this.currentCapital, user.currentCapital);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, startCapital, currentCapital);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    startCapital: ").append(toIndentedString(startCapital)).append("\n");
    sb.append("    currentCapital: ").append(toIndentedString(currentCapital)).append("\n");
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

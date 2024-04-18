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
 * UsersBody
 */
@Validated
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-04-05T01:57:44.169955+02:00[Europe/Brussels]")


public class UsersBody   {
  @JsonProperty("newStartCapital")
  private BigDecimal newStartCapital = null;

  public UsersBody newStartCapital(BigDecimal newStartCapital) {
    this.newStartCapital = newStartCapital;
    return this;
  }

  /**
   * Get newStartCapital
   * @return newStartCapital
   **/
  @Schema(example = "10000.0", description = "")
  
    @Valid
    public BigDecimal getNewStartCapital() {
    return newStartCapital;
  }

  public void setNewStartCapital(BigDecimal newStartCapital) {
    this.newStartCapital = newStartCapital;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UsersBody usersBody = (UsersBody) o;
    return Objects.equals(this.newStartCapital, usersBody.newStartCapital);
  }

  @Override
  public int hashCode() {
    return Objects.hash(newStartCapital);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsersBody {\n");
    
    sb.append("    newStartCapital: ").append(toIndentedString(newStartCapital)).append("\n");
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

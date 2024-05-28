package ee.cgi.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanDto {

    private Long id;
    @NotNull(message = "value has to be present")
    private BigDecimal principalAmount;
    @DecimalMin(value = "0", message = "Min interest value is 0")
    @DecimalMax(value = "100", message = "Max interest value is 100")
    @Digits(integer = 3, fraction = 2, message = "Interest rate needs to be ###.##")
    private BigDecimal interestRate;
    @Min(value = 1, message = "Min term value is 1")
    private Integer term;

    private BigDecimal npv;
    private BigDecimal oldNpv;
}

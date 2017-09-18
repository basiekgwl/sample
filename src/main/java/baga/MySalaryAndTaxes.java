package baga;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MySalaryAndTaxes {

    private BigDecimal salaryBrutto;
    private BigDecimal taxValue;
    private BigDecimal salaryNetto;
    private String reportDate;
}

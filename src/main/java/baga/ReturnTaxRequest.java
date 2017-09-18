package baga;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import validators.AmountValue;
import validators.DateWithoutTime;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReturnTaxRequest {

    @DecimalMin(value = "10", message = "<howManyHours> min value = 10")
    private BigDecimal howManyHours;


    @Min(value = 1, message = "<hourPrice> min value = 1")
    private BigDecimal hourPrice;


    @Min(value = 0, message = "<spending> min value = 0")
    private BigDecimal spending;


    @Digits(integer = 10, fraction = 2, message = "Error - Valid Pattern is: integer 10 and fraction 2: XXXXXXXXXX.XX")
    @DecimalMin(value = "749.94", message = "Min value of <socialAmount> should be 749.94")
    @NotNull(message = "Value of socialAmount should not be NULL")
    private BigDecimal socialAmount;


    @AmountValue
    @NotNull(message = "Value of <healthBaseAmount> should not be NULL")
    private BigDecimal healthBaseAmount;


    @AmountValue
    @NotNull(message = "Value of <laborAmount> should not be NULL")
    private BigDecimal laborAmount;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    @DateWithoutTime
    private Date someDateInPast;

    public String getDateInCorrectFormat(Date date) {
        DateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dt.format(date);
    }

    @Override
    public String toString() {
        return "Salary and Tax preconditions: [howManyHours:  " + howManyHours + ", hourPrice: " + hourPrice + ", spending: " + spending + " and DATE in past:   ]";
    }

}

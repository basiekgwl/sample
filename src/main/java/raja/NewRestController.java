package raja;

import baga.IEmployeeAndTaxes;
import baga.MySalaryAndTaxes;
import baga.ReturnTaxRequest;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class NewRestController implements IEmployeeAndTaxes {

    @Override
    public BigDecimal mySalaryNetto() {
        return null;
    }

    @Override
    public BigDecimal mySalaryDecreasedByCosts(double priceByHour, double howManyHours, double spendingValue) {
        return null;
    }

    @Override
    public MySalaryAndTaxes returnTax(ReturnTaxRequest request) {
        return null;
    }
}

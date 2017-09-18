package baga;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;

public interface IEmployeeAndTaxes {

    BigDecimal mySalaryNetto();

    @RequestMapping(value = "/mySalaryNetto", method = RequestMethod.POST)
    BigDecimal mySalaryDecreasedByCosts(double priceByHour, double howManyHours, double spendingValue);

    @RequestMapping(value = "/myTax", method = RequestMethod.POST)
    MySalaryAndTaxes returnTax(ReturnTaxRequest request);
}

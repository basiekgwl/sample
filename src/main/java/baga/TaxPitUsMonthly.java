package baga;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Slf4j
@RestController
public class TaxPitUsMonthly extends AbstractZusPremium implements IEmployeeAndTaxes {

    private double salaryBase;
    private double spending;
    public static final Double TAX_VALUE = 18.00;

    public TaxPitUsMonthly(double salary, double spending, double socialInsurance, double healthBaseInsurance, double labourFund) {

        this.salaryBase = salary;
        this.spending = spending;
        super.setHealthBaseInsurance(healthBaseInsurance);
        super.setLabourFund(labourFund);
        super.setSocialInsurance(socialInsurance);
    }

    public TaxPitUsMonthly() {
    }

    public BigDecimal getHealthInsurance() {
        return BigDecimal.valueOf(getHealthBaseInsurance() * 0.09).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public double getHealthBaseInsurance() {
        return super.getHealthBaseInsurance();
    }

    @Override
    public double getSocialInsurance() {
        return super.getSocialInsurance();
    }

    @Override
    public double getLabourFund() {
        return super.getLabourFund();
    }

    private BigDecimal returnTaxInMonth(double salary, double spending) {
        log.debug("Salary Brutto: " + BigDecimal.valueOf(salary).setScale(2, BigDecimal.ROUND_HALF_UP) + " PLN");
        double taxAsDouble = TAX_VALUE / 100;
        log.debug("Interest rate: " + new BigDecimal(TAX_VALUE).setScale(0, BigDecimal.ROUND_DOWN) + "%");
        double baseValue = returnSalaryDecreasedByCosts(salary, spending) * 100;
        double tax = (baseValue * taxAsDouble) - (getHealthBaseInsurance() * 100 * 7.75 / 100);
        log.debug("Tax to pay: " + getValueInProperFormat(tax));
        return getValueInProperFormat(tax);
    }

    private BigDecimal getValueInProperFormat(double amount) {
        double value = amount * 0.01;
        return BigDecimal.valueOf(value).setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    /// **************** start ***************************************

    private BigDecimal salaryBase(BigDecimal howManyHours, BigDecimal hourSalary) {
        return howManyHours.multiply(hourSalary);
    }

    private BigDecimal baseValue(BigDecimal salaryBase, BigDecimal spending, BigDecimal socialAmount, BigDecimal laborAmount) {
        return salaryBase.subtract(spending).subtract(laborAmount).subtract(socialAmount);
    }

    private BigDecimal taxValue(BigDecimal baseValue, BigDecimal healthBaseAmount) {
        return baseValue.multiply(BigDecimal.valueOf(TAX_VALUE)).subtract(healthBaseAmount.multiply(BigDecimal.valueOf(7.75)));
    }

    private BigDecimal getHealthInsurance(BigDecimal healthBaseInsurance) {
        return healthBaseInsurance.multiply(BigDecimal.valueOf(0.09)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal getValueInProperFormat(BigDecimal amount) {
        BigDecimal value = amount.multiply(BigDecimal.valueOf(0.01));
        return value.setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    public MySalaryAndTaxes returnTax(@Valid ReturnTaxRequest request) {

        BigDecimal salaryBaseValue = salaryBase(request.getHowManyHours(), request.getHourPrice());
        BigDecimal baseValue = baseValue(salaryBaseValue, request.getSpending(), request.getSocialAmount(), request.getLaborAmount());
        BigDecimal tax = taxValue(baseValue, request.getHealthBaseAmount());

        BigDecimal pit5Value = getValueInProperFormat(tax);
        log.info("PIT5 - TAX is: " + pit5Value + "\n");

        BigDecimal salaryNetto = baseValue.subtract(pit5Value)
                .subtract(getHealthInsurance(request.getHealthBaseAmount()))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal salaryBrutto = salaryBaseValue.setScale(2, BigDecimal.ROUND_HALF_UP);

        String reportDate = request.getDateInCorrectFormat(new Date());
        log.info("Request data: " + request);

        return new MySalaryAndTaxes(salaryBrutto, pit5Value, salaryNetto, reportDate);
    }

    //******************** stop ************************************

    private double returnSalaryDecreasedByCosts(double salaryBase, double spending) {
        return (salaryBase - spending - getLabourFund() - getSocialInsurance());
    }

    private BigDecimal salaryNettoUsingIncome(BigDecimal salaryNetto) {
        BigDecimal newNettoSalary = salaryNetto.subtract(getHealthInsurance()).setScale(2, BigDecimal.ROUND_HALF_UP);
        log.info("My salary NETTO is: " + newNettoSalary + "\n");
        return newNettoSalary;
    }

    @Override
    public BigDecimal mySalaryNetto() {
        log.debug("Salary value: " + BigDecimal.valueOf(salaryBase).setScale(2, BigDecimal.ROUND_HALF_UP) + " PLN");
        log.debug("Spending value: " + BigDecimal.valueOf(spending).setScale(2, BigDecimal.ROUND_HALF_UP) + " PLN");
        BigDecimal incomeBeforeTaxation = BigDecimal.valueOf(returnSalaryDecreasedByCosts(salaryBase, spending));
        BigDecimal salaryNetto = incomeBeforeTaxation.subtract(returnTaxInMonth(salaryBase, spending));
        return salaryNettoUsingIncome(salaryNetto);
    }

    @Override
    public BigDecimal mySalaryDecreasedByCosts(double priceByHour, double howManyHours, double spendingValue) {
        double salaryValue = priceByHour * howManyHours;
        log.debug("Salary value: " + BigDecimal.valueOf(salaryValue).setScale(2, BigDecimal.ROUND_HALF_UP) + " PLN");
        log.debug("Spending value: " + BigDecimal.valueOf(spendingValue).setScale(2, BigDecimal.ROUND_HALF_UP) + " PLN");

        BigDecimal incomeBeforeTaxation = BigDecimal.valueOf(returnSalaryDecreasedByCosts(salaryValue, spendingValue));
        BigDecimal salaryNetto = incomeBeforeTaxation.subtract(returnTaxInMonth(salaryValue, spendingValue));
        return salaryNettoUsingIncome(salaryNetto);
    }
}
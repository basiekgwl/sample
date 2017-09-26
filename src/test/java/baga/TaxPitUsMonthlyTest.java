package baga;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.math.BigDecimal;

@Slf4j
public class TaxPitUsMonthlyTest extends TestCase {

    @Test
    public void testPitValue() {

        double socialAmount = 749.94;
        double healthBaseAmount = 3303.13;
        double laborAmount = 62.67;

        double mySalaryBase = 172.80 * 56;
        double costs = 0;

        BigDecimal healthAmount = new BigDecimal("297.28");

        TaxPitUsMonthly myTaxData = new TaxPitUsMonthly(mySalaryBase, costs, socialAmount, healthBaseAmount, laborAmount);

        assertEquals(myTaxData.getHealthBaseInsurance(), healthBaseAmount);
        assertEquals(myTaxData.getSocialInsurance(), socialAmount);
        assertEquals(myTaxData.getLabourFund(), laborAmount);
        assertEquals(myTaxData.getHealthInsurance(), healthAmount);

        BigDecimal myIncomeNetto = myTaxData.mySalaryNetto();
        assertEquals(myIncomeNetto, new BigDecimal("7226.91"));

        mySalaryBase = 168 * 60;
        myTaxData.setSalaryBase(mySalaryBase);

        myIncomeNetto = myTaxData.mySalaryNetto();
        assertEquals(myIncomeNetto, new BigDecimal("7558.11"));

        mySalaryBase = 160 * 60;
        myTaxData.setSalaryBase(mySalaryBase);

        myIncomeNetto = myTaxData.mySalaryNetto();
        assertEquals(myIncomeNetto, new BigDecimal("7164.11"));

        mySalaryBase = 162 * 50;
        myTaxData.setSalaryBase(mySalaryBase);

        myIncomeNetto = myTaxData.mySalaryNetto();
        assertEquals(myIncomeNetto, new BigDecimal("5934.11"));


        // interface test

        IEmployeeAndTaxes myTestInterface = new TaxPitUsMonthly(mySalaryBase, costs, socialAmount, healthBaseAmount, laborAmount);
        myTestInterface.mySalaryNetto();
        myIncomeNetto = myTestInterface.mySalaryDecreasedByCosts(172, 60, 0);
        assertEquals(myIncomeNetto, new BigDecimal("7755.11"));

    }


}

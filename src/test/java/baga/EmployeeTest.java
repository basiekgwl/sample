package baga;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

@Slf4j
public class EmployeeTest  {

    @Test
    public void myEmployeeTest() {

        IEmployee baga = new Employee("Ginny Weasley", new BigDecimal("7400.69"), 2003, 1, 3);
        IEmployee dazb = new Employee("Severus Snape", new BigDecimal("9454.75"), 1996, 3, 20);
        IEmployee anbo = new Employee("Andrzej Borys", new BigDecimal("8341.99"), 1998, 6, 2);

        int result = baga.compareTo(baga);
        Assert.assertEquals(result, 0);
        result = dazb.compareTo(baga);
        Assert.assertEquals(result, 1);
        result = anbo.compareTo(baga);
        Assert.assertEquals(result, 1);
        result = baga.compareTo(anbo);
        Assert.assertEquals(result, -1);

        BigDecimal newSalaryTemp = baga.tripleSalary();
        Assert.assertEquals(newSalaryTemp, new BigDecimal("22202.07") );
        Assert.assertEquals(baga.getSalary(), new BigDecimal("7400.69") );
    }

    @Test
    public void raiseSalaryTest() {

        IEmployee baga = new Employee("Ginny Weasley", new BigDecimal("7400.69"), 2003, 1, 3);
        IEmployee anbo = new Employee("Andrzej Borys", new BigDecimal("8341.99"), 1998, 6, 2);

        baga.raiseSalary(BigDecimal.valueOf(10));
        int result = baga.compareTo(anbo);
        Assert.assertEquals(result,-1);
        baga.raiseSalary(BigDecimal.valueOf(3));
        result = baga.compareTo(anbo);
        Assert.assertEquals(result, 1);
    }
}

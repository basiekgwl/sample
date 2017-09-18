package baga;

import java.math.BigDecimal;

public interface IEmployee {

    BigDecimal raiseSalary(BigDecimal byPercent);

    BigDecimal getSalary();

    BigDecimal tripleSalary();

    int compareTo(IEmployee otherEmployee);

}

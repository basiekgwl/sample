package baga;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;

@Setter
@Getter
@ToString
@Slf4j
public class Employee implements IEmployee {

    private String name;
    private BigDecimal salary;
    private String hireDay;
    private int id = assignId();
    private static int nextId = 1;

    private Person person;
    private static int otherId = 1;

    public Employee(String aName, BigDecimal aSalary, int year, int month, int day) {
        name = aName;
        salary = aSalary;
        hireDay = returnDateAsString(year, month, day);
    }

    public Employee(Person person, BigDecimal aSalary, int year, int month, int day) {
        this.person = person;
        salary = aSalary;
        hireDay = returnDateAsString(year, month, day);
    }

    public String getFullName() {
        return person.getFullName();
    }

    public String getNip() {
        return person.getNip();
    }

    public String getPesel() {
        return person.getPesel();
    }

    public String getAddress() {
        return person.getAddress();
    }

    public Employee() {
        name = "";
        salary = BigDecimal.ZERO;
        hireDay = "";
    }

    @Override
    public BigDecimal raiseSalary(BigDecimal byPercent) {

        log.info("Salary base value: " + salary);
        BigDecimal value = byPercent.divide(new BigDecimal(100), 2, BigDecimal.ROUND_UNNECESSARY);
        salary = salary.multiply(value).add(salary).setScale(2, BigDecimal.ROUND_HALF_UP);
        log.info("Value after raise: " + salary);
        return salary;
    }

    private static int assignId() {
        int r = nextId;
        nextId++;
        return r;
    }

    private String returnDateAsString(int year, int month, int day) {
        HelperForDate helper = new HelperForDate();
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
        return helper.displayCurrentDateInCorrectFormat(calendar, "dd MMMM YYYY");
    }

    @Override
    public BigDecimal tripleSalary() {
        BigDecimal tripleSalary = salary.multiply(BigDecimal.valueOf(3));
        return tripleSalary;
    }

    @Override
    public int compareTo(IEmployee otherEmployee) {

        String str1 = "Both values are equal ";
        String str2 = "First Value is greater ";
        String str3 = "Second value is greater";
        String errorMsg = "Invalid result from method compareTo";

        int result = this.salary.compareTo(otherEmployee.getSalary());

        if (result == 0) {
            log.info(str1);
        } else if (result == 1) {
            log.info(str2);
        } else if (result == -1) {
            log.info(str3);
        } else {
            log.info(errorMsg);
        }
        return result;
    }

    //blok inicjujący obiektu
    {
        id = otherId;
        otherId++;
    }
}

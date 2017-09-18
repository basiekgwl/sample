package baga;

import junit.framework.TestCase;
import lombok.EqualsAndHashCode;
import org.junit.rules.ExpectedException;
import java.math.BigDecimal;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;

@Slf4j
@ToString
@EqualsAndHashCode
public class SimpleTest extends TestCase {

    protected BigDecimal salaryEmployee1;
    protected BigDecimal salaryEmployee2;
    protected BigDecimal salaryEmployee3;
    protected BigDecimal salaryEmployee4;

    @Override
    protected void setUp() {
        salaryEmployee1 = new BigDecimal("4000.00");
        salaryEmployee2 = new BigDecimal("5000.00");
        salaryEmployee3 = new BigDecimal("6500.00");
        salaryEmployee4 = new BigDecimal("7200.00");
    }

    public BigDecimal myResult;

    @Rule
    public ExpectedException exception = ExpectedException.none();
    public void testDivideByZero() {

        Integer[] myArray = new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        for (Integer element : myArray) {

            try {
                double result = (8.00 / element);
                myResult = new BigDecimal(result).setScale(2, BigDecimal.ROUND_UP);
                log.info("Element = " + element + " Result: " + myResult);
            } catch (Exception e) {
                exception.expect(ArithmeticException.class);
                log.info("Error msg: division by zero exception");
            }
        }
    }

    public void testEquals() {
        assertEquals(12, 12);
        assertEquals(12L, 12L);
        assertEquals(new Long(12), new Long(12));

//        assertEquals("Size", 12, 13);
//        assertEquals("Capacity", 12.0, 11.99, 0.0);
    }

    public void testEmployeeData() {

        Employee baga = new Employee("Ginny Weasley", new BigDecimal("7400.69"), 2003, 1, 3);
        Employee dazb = new Employee("Severus Snape", new BigDecimal("9454.75"), 1996, 3, 20);
        Employee anbo = new Employee("Andrzej Borys", new BigDecimal("8341.99"), 1998, 6, 2);


        log.info("Baga:  " + baga.getName() + " ID: " + baga.getId());
        log.info("Dazb:  " + dazb.getName() + " ID: " + dazb.getId());
        log.info("Anbo:  " + anbo.getName() + " ID: " + anbo.getId());
        assertEquals(baga.getName(), "Ginny Weasley");
        assertEquals(baga.getHireDay(), "03 stycznia 2003");

        assertEquals(baga.raiseSalary(new BigDecimal(10)), new BigDecimal("8140.76"));
        assertEquals(dazb.raiseSalary(new BigDecimal(12)), new BigDecimal("10589.32"));
        assertEquals(anbo.raiseSalary(new BigDecimal(16)), new BigDecimal("9676.71"));
    }

    public void testEmployeeAllData() {
        Person harry = new Person();

        String[] personData = new String[]{"Harry Potter", "5531234412", "80040312707", "ul. Życzkowskiego 20, 31-864 Kraków"};
        harry.setFullName(personData[0]);
        harry.setNip(personData[1]);
        harry.setPesel(personData[2]);
        harry.setAddress(personData[3]);

        BigDecimal percentUp = new BigDecimal(20);
        Employee harryAll = new Employee(harry, salaryEmployee2, 2016, 3, 16);

        assertEquals(harryAll.getFullName(), personData[0]);
        assertEquals(harryAll.getNip(), personData[1]);
        assertEquals(harryAll.getAddress(), personData[3]);
        assertEquals(harryAll.raiseSalary(percentUp), new BigDecimal("6000.00"));
        assertNotSame(harryAll.getNip(), "5531234413");
    }

    public void testEmployeeNotAllData() {
        Person hermiona = new Person();

        String[] personData = new String[]{"Hermiona Granger", "5531234412", "ul. Życzkowskiego 20, 31-864 Kraków"};
        hermiona.setFullName(personData[0]);
        hermiona.setNip(personData[1]);
        hermiona.setAddress(personData[2]);

        Employee hermionaWithoutPesel = new Employee(hermiona, salaryEmployee2, 2016, 3, 16);

        log.debug("Employee: " + hermionaWithoutPesel);

        assertEquals(hermionaWithoutPesel.getPesel(), null);
        assertEquals(hermionaWithoutPesel.getHireDay(), "16 marca 2016");

        assertEquals(hermionaWithoutPesel.tripleSalary().toString(), "15000.00");
        assertEquals(hermionaWithoutPesel.tripleSalary(), new BigDecimal("15000.00"));
    }

//    public static Test suite() {
//        return new TestSuite(SimpleTest.class);
//    }
//
//    public static void main(String[] args) {
//        junit.textui.TestRunner.run(suite());
//    }
}

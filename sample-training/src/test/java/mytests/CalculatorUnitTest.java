package mytests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.CalcService;
import services.Calculator;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@Component
@ContextConfiguration("../../resources/MyBeans.xml")
public class CalculatorUnitTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        //ta metoda będzie wywołana raz, przed wszystkimi testami
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        //ta metoda będzie wywołana raz, po wszystkich testach
    }

    @Before
    public void setUp() throws Exception {
        //ta metoda będzie wywołana przed każdym testem
    }

    @After
    public void tearDown() throws Exception {
        //ta metoda będzie wywołana po każdym teście
    }

//    ApplicationContext myContext = new ClassPathXmlApplicationContext("MyBeans.xml");
////    Calculator calculator = (Calculator) myContext.getBean("calcultor");
//    CalcService calcService = (CalcService) myContext.getBean("calcService");

    @Autowired
    CalcService calcService;

    @Test
    public void sumResultTest() {
//        Calculator calc = new Calculator();
        Assert.assertEquals("0*0 musi się równać 0", 0, Calculator.multiply(0, 0));
    }

    @Test
    public void returnNettoFvat() {

        BigDecimal valueBeforeTax = BigDecimal.valueOf(7438.48);
        BigDecimal valueAfterTax = BigDecimal.valueOf(6050);
        Assert.assertEquals("value netto", valueBeforeTax, Calculator.returnNettoFvat(valueAfterTax));
    }

    @Test
    public void verifyTaxValue() {
        BigDecimal valueBeforeTax = BigDecimal.valueOf(7438.48);
        BigDecimal taxValue = BigDecimal.valueOf(1396);
        Assert.assertEquals("expected value", taxValue, calcService.doSomeNewAction(valueBeforeTax));
    }
}

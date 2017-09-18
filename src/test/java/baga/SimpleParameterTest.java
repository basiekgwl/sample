package baga;

import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@Slf4j
@RunWith(Parameterized.class)
public class SimpleParameterTest{


    @BeforeClass
    public static void beforeTest() {
        log.debug("BeforeClass test - message");
    }

    @AfterClass
    public static void afterTest() {
        log.debug("AfterClass test - message");
    }

    @Parameterized.Parameter(0)
    public int m1;
    @Parameterized.Parameter(1)
    public int m2;
    @Parameterized.Parameter(2)
    public int result;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{1, 2, 2}, {2, 3, 6}, {2, 4, 8}};
        return Arrays.asList(data);
    }

    @Test
    public void myNewTest() {

        HelperForDate tester = new HelperForDate();
        Assert.assertEquals("Result", result, tester.multiply(m1, m2));
    }


}

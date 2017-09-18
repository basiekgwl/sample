package baga;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class HelperForDate {

    protected String displayCurrentDateInCorrectFormat(GregorianCalendar date, String datePattern) {

        SimpleDateFormat formatWithMonthName = new SimpleDateFormat(datePattern);
        return formatWithMonthName.format(date.getTime());
    }

    public int multiply(int i, int j) {
        return i * j;
    }
}

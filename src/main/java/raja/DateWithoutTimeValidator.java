package raja;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import validators.DateWithoutTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateWithoutTimeValidator implements ConstraintValidator<DateWithoutTime, Date> {

    @Override
    public void initialize(DateWithoutTime constraintAnnotation) {
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        if (date == null) {
            return true;
        }

        DateFormat dt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Date truncatedDate = DateUtils.truncate(date, Calendar.DAY_OF_MONTH);

        String myDate = dt.format(date);
        String truncDate = dt.format(truncatedDate);

        log.info("Date - DAY: " + myDate + " ------- " + truncDate);

        return date.equals(truncatedDate);
    }
}

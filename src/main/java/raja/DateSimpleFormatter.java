package raja;

import validators.DateCustomFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateSimpleFormatter implements ConstraintValidator<DateCustomFormatter, String> {


    @Override
    public void initialize(DateCustomFormatter constraintAnnotation) {
        //Not supported
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}

package mybatis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {

    // @RequestHandler methods

    // Exception handling methods

    // Convert a predefined exception to an HTTP Status code
    @ResponseStatus(value = HttpStatus.CONFLICT,
            reason = "Data integrity violation")  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict() {
        // Nothing to do
    }

    // Specify name of a specific view that will be used to display the error:
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError() {
        // Nothing to do.  Returns the logical view name of an error page, passed
        // to the view-resolver(s) in usual way.
        // Note that the exception is NOT available to this view (it is not added
        // to the model) but see "Extending ExceptionHandlerExceptionResolver"
        // below.
        return "databaseError: SQLException or DataAccessException";
    }


}




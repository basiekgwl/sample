package mybatis.controller.interfaces;

import com.viscomp.services.ErrorMsg;
import mybatis.services.CommonErrorMsg;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;

@ControllerAdvice
public interface IAbstractController {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    ModelMap handleBadRequests(HttpServletRequest req, ConstraintViolationException ex);

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    ModelMap handleTypeMismatch(HttpServletRequest req, MethodArgumentTypeMismatchException ex);

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    ModelMap handleMissingParam(HttpServletRequest req, MissingServletRequestParameterException ex);

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = CommonErrorMsg.DATA_CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    void conflict();

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    String databaseError();

}

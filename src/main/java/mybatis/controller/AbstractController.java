package mybatis.controller;

import lombok.extern.slf4j.Slf4j;
import mybatis.controller.interfaces.IAbstractController;
import com.viscomp.services.ErrorMsg;
import mybatis.services.ExceptionData;
import mybatis.services.JsonRespAttrName;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

import static mybatis.services.CustomJsonResponses.getModelAndView;

@Slf4j
abstract class AbstractController implements IAbstractController {

    public ModelMap handleBadRequests(HttpServletRequest req, ConstraintViolationException ex) {

        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        log.error("getConstraintViolations: " + constraintViolations, ex);

        ModelAndView mav = getModelAndView();
        mav.addObject(JsonRespAttrName.ERROR_CODE, "ConstraintViolationException");
        mav.addObject(JsonRespAttrName.MESSAGE, constraintViolations.toString());
        mav.addObject(JsonRespAttrName.PATH, req.getRequestURL());

        return mav.getModelMap();
    }

    public ModelMap handleTypeMismatch(HttpServletRequest req, MethodArgumentTypeMismatchException ex) {

        String message = String.format("'%s' should be a valid '%s' but '%s' isn't",
                ex.getName(), ex.getRequiredType().getSimpleName(), ex.getValue());
        log.error(message + " Request: " + req.getRequestURL(), ex);

        ModelAndView mav = getModelAndView();
        mav.addObject(JsonRespAttrName.EXCEPTION_DESCRIPTION, ex.getErrorCode());
        mav.addObject(JsonRespAttrName.MESSAGE, message);
        mav.addObject(JsonRespAttrName.PATH, req.getRequestURL());

        mav.setViewName(message);
        Object className = mav.getModelMap().getClass();

        log.debug("Model class: " + className);

        log.debug("Model: " + mav.getModelMap());
        return mav.getModelMap();
    }

    public ModelMap handleMissingParam(HttpServletRequest req, MissingServletRequestParameterException ex) {

        log.debug("Request: " + req.getRequestURL() + " raised " + ex);
        log.debug(ErrorMsg.MISSING_PARAM_MSG);

        ModelAndView mav = getModelAndView();
        mav.addObject(JsonRespAttrName.EXCEPTION_DESCRIPTION, ExceptionData.MISSING_SERVLET_PARAM_EXC);
        mav.addObject(JsonRespAttrName.MESSAGE, ex.getMessage());
        mav.addObject(JsonRespAttrName.CUSTOM_MSG, ErrorMsg.MISSING_PARAM_MSG);
        mav.addObject(JsonRespAttrName.PATH, req.getRequestURL());

        mav.setViewName(ErrorMsg.MISSING_PARAM_MSG);

        log.info("Model class: " + mav.getModelMap().getClass());

        log.info("Model: " + mav.getModelMap());
        return mav.getModelMap();
    }

    // Convert a predefined exception to an HTTP Status code
    public void conflict() {
        // Nothing to do
    }

    // Specify name of a specific view that will be used to display the error:
    public String databaseError() {
        // Nothing to do.  Returns the logical view name of an error page, passed
        // to the view-resolver(s) in usual way.
        // Note that the exception is NOT available to this view (it is not added
        // to the model) but see "Extending ExceptionHandlerExceptionResolver"
        // below.
        return "databaseError: SQLException or DataAccessException";
    }
}
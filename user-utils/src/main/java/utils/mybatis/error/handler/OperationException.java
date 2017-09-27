package utils.mybatis.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="UNEXPECTED ERROR")
public class OperationException extends RuntimeException{

    private static final long serialVersionUID = 3935230123455340039L;

}

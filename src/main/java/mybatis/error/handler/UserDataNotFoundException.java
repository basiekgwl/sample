package mybatis.error.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Request is INVALID !!!")  // 400
public class UserDataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3935230281455340039L;

    String errorMessage;

    String errorCode;

    public UserDataNotFoundException(String errorMessage, String errorCode) {
        super();
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public UserDataNotFoundException() {
    }
}

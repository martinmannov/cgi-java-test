package ee.cgi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception used when validations related to loans fail
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class LoanValidationException extends RuntimeException {

    public LoanValidationException(String message) {
        super(message);
    }
}

package kodlama.io.rentACar;

import kodlama.io.rentACar.core.utilities.exception.BusinessException;
import kodlama.io.rentACar.core.utilities.exception.ExceptionDetails;
import kodlama.io.rentACar.core.utilities.exception.ValidationExceptionDetails;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@SpringBootApplication
@RestControllerAdvice
public class RentACarApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentACarApplication.class, args);
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ExceptionDetails handleBusinessException(BusinessException businessException) {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(businessException.getMessage());

        return exceptionDetails;
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ExceptionDetails handleBusinessException(MethodArgumentNotValidException exception) {
        ValidationExceptionDetails exceptionDetails = new ValidationExceptionDetails();
        exceptionDetails.setMessage("VALIDATION_EXCEPTION");
        exceptionDetails.setValidationExceptions(new HashMap<>());

        for (FieldError error : exception.getFieldErrors()) {
            exceptionDetails
                    .getValidationExceptions()
                    .put(error.getField(), error.getDefaultMessage());
        }


        return exceptionDetails;
    }

    @Bean
    @Scope(value = "singleton")
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}

package kodlama.io.rentACar.core.utilities.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationExceptionDetails extends ExceptionDetails{

    private HashMap<String,String> validationExceptions;

}

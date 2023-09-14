package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.exception;

import cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.exception.customExceptions.NoSuchElementException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> invalidUser(NoSuchElementException ex){
        Map<String, String> mapError = new HashMap<>();
        mapError.put("ErrorMessage", ex.getMessage());
        return new ResponseEntity<>(mapError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<?> connectionException(ConnectException ex){
        Map<String, String> mapError = new HashMap<>();
        mapError.put("ErrorMessage", ex.getMessage());
        return new ResponseEntity<>(mapError, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<?> clientResponseException(WebClientResponseException wcre){
        Map<String, String> mapError = new HashMap<>();
        mapError.put("ErrorMessage", wcre.getMessage());
        return new ResponseEntity<>(mapError, HttpStatus.CONFLICT);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }








}
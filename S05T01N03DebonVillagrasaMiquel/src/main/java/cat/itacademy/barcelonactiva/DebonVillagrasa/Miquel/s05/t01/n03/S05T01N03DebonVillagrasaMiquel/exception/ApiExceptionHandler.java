package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NoSuchElement.class)
    public ResponseEntity<?> invalidUser(NoSuchElement ex){
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

}
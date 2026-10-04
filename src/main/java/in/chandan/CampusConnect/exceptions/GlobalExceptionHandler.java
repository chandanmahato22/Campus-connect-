package in.chandan.CampusConnect.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex,
                                                 HttpServletRequest req){

        ErrorResponse err = new ErrorResponse();
        err.setMsg(ex.getMessage());
        err.setPath(req.getRequestURI());
        err.setStatus(404);
        err.setError("NOT FOUND");
        err.setTimeStamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(err);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    ResponseEntity<ErrorResponse> handleAlreadyExist(ResourceAlreadyExistException ex,
                                              HttpServletRequest req){

        ErrorResponse err = new ErrorResponse();
        err.setMsg(ex.getMessage());
        err.setPath(req.getRequestURI());
        err.setStatus(409);
        err.setError("CONFLICT");
        err.setTimeStamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(err);
    }

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<ErrorResponse> handleBadRequest(ResourceNotFoundException ex,
                                            HttpServletRequest req){

        ErrorResponse err = new ErrorResponse();
        err.setMsg(ex.getMessage());
        err.setPath(req.getRequestURI());
        err.setStatus(400);
        err.setError("CONFLICT");
        err.setTimeStamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(err);
    }

    @ExceptionHandler(UnauthorizedException.class)
    ResponseEntity<ErrorResponse> handleBadRequest(UnauthorizedException ex,
                                                   HttpServletRequest req){

        ErrorResponse err = new ErrorResponse();
        err.setMsg(ex.getMessage());
        err.setPath(req.getRequestURI());
        err.setStatus(401);
        err.setError("UNAUTHORIZED ");
        err.setTimeStamp(LocalDateTime.now());

        return ResponseEntity.status(401)
                .body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex,
                                                            HttpServletRequest req){

        String msg = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ":" + error.getDefaultMessage())
                .collect(Collectors.joining(" , "));


        ErrorResponse err = new ErrorResponse();
        err.setMsg(ex.getMessage());
        err.setPath(req.getRequestURI());
        err.setStatus(400);
        err.setError("VALIDATION FAILED !");
        err.setTimeStamp(LocalDateTime.now());

        return ResponseEntity.status(400).body(err);
    }

    @ExceptionHandler(TooManyRequestException.class)
    ResponseEntity<ErrorResponse> tooManyRequestHandler(TooManyRequestException ex,
                                                            HttpServletRequest req){

        ErrorResponse err = new ErrorResponse();
        err.setMsg(ex.getMessage());
        err.setPath(req.getRequestURI());
        err.setStatus(429);
        err.setError("TOO MANY REQUEST !");
        err.setTimeStamp(LocalDateTime.now());

        return ResponseEntity.status(429).body(err);
    }
    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<ErrorResponse> tooManyRequestHandler(AuthenticationException ex,
                                                        HttpServletRequest req){

        ErrorResponse err = new ErrorResponse();
        err.setMsg(ex.getMessage());
        err.setPath(req.getRequestURI());
        err.setStatus(401);
        err.setError("INVALID ID PASSWORD !");
        err.setTimeStamp(LocalDateTime.now());

        return ResponseEntity.status(401).body(err);
    }

}

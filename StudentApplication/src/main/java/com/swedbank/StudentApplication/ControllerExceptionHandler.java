package com.swedbank.StudentApplication;
import java.util.Date;
import com.swedbank.StudentApplication.errors.ErrorMessage;
import com.swedbank.StudentApplication.student.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorMessage> studentNotFoundException(StudentNotFoundException ex, WebRequest request){
        ErrorMessage error = ErrorMessage.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<ErrorMessage>(error, HttpStatus.NOT_FOUND);
    }
}
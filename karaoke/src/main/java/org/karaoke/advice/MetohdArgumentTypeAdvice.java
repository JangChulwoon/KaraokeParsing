package org.karaoke.advice;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class MetohdArgumentTypeAdvice {

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity requestWrongCategory(Exception e){
        return ResponseEntity.badRequest().body("Invaild category's or company's input value.");
    }
}

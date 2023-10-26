package br.com.car.rental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;

/**
 * Controller advice that handles exceptions thrown by the controllers.
 */
@RestControllerAdvice
public class CarRentalControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public String handleRecordNotFoundException(RecordNotFoundException e) {
    	return new ResponseMessage(HttpStatus.NOT_FOUND.value(), e.getMessage()).toString();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException e) {
        return new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "Invalid fields").toString();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(BusinessException e) {
        return new ResponseMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage()).toString();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public String handleUsernameNotFoundException(UsernameNotFoundException e) {
    	return new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "Invalid login or password").toString();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    	return new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "Invalid fields").toString();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolationException(ConstraintViolationException e) {
    	return new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "Invalid fields").toString();
    }
}

package cl.somosafac.afacbackend.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Manejo de excepciones de recursos no encontrados
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Manejo de excepciones de validación personalizadas
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Error de validación",
            ex
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Manejo de excepciones de violación de restricciones de base de datos
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.CONFLICT.value(),
            "Error de integridad de datos. Posible duplicado o violación de restricción",
            ex
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // Manejo de excepciones de autenticación
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.UNAUTHORIZED.value(),
            "Credenciales inválidas",
            ex
        );
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    // Manejo de excepciones de JWT expirado
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.UNAUTHORIZED.value(),
            "Token JWT expirado",
            ex
        );
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    // Manejo de excepciones de firma JWT
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponse> handleJwtSignatureException(SignatureException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.UNAUTHORIZED.value(),
            "Token JWT inválido",
            ex
        );
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    // Manejo de excepciones de usuario no encontrado
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "Usuario no encontrado",
            ex
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Manejo de excepciones de validación de constraints
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Error de validación de restricciones"
        );
        ex.getConstraintViolations().forEach(violation -> 
            error.addError(violation.getPropertyPath() + ": " + violation.getMessage())
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Manejo de excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Ha ocurrido un error inesperado",
            ex
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
} 
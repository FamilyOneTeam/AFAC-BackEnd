package cl.somosafac.afacbackend.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse {
    private int status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<String> errors;

    public ErrorResponse() {
        timestamp = LocalDateTime.now();
        errors = new ArrayList<>();
    }

    public ErrorResponse(int status) {
        this();
        this.status = status;
    }

    public ErrorResponse(int status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(int status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public void addError(String error) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(error);
    }
} 
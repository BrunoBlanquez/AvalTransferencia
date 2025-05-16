package com.example.AgendamentoTransf.Exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();

        // Caso seja um erro de formatação (ex: String para Long)
        if (cause instanceof InvalidFormatException formatException) {
            String fieldName = formatException.getPath().get(0).getFieldName();
            String invalidValue = formatException.getValue().toString();
            String message = "Campo '" + fieldName + "' recebeu valor inválido: '" + invalidValue + "'. Deve conter apenas números.";
            return ResponseEntity.badRequest().body(message);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao processar requisição.");
    }
}

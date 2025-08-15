package com.cooperados.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Handler global para tratamento de exceções da aplicação
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções de validação de entrada
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Erro de Validação")
                .message("Dados de entrada inválidos")
                .details(errors)
                .path(request.getDescription(false))
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
     * Trata exceção quando cooperado não é encontrado
     */
    @ExceptionHandler(CooperadoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleCooperadoNaoEncontrado(
            CooperadoNaoEncontradoException ex, WebRequest request) {
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Cooperado Não Encontrado")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Trata exceção quando documento já existe
     */
    @ExceptionHandler(DocumentoJaExisteException.class)
    public ResponseEntity<ErrorResponse> handleDocumentoJaExiste(
            DocumentoJaExisteException ex, WebRequest request) {
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Documento Já Existe")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Trata exceções genéricas de argumento ilegal
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Argumento Inválido")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
     * Trata exceções genéricas não mapeadas
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Erro Interno do Servidor")
                .message("Ocorreu um erro inesperado. Tente novamente mais tarde.")
                .path(request.getDescription(false))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    /**
     * Classe interna para representar a resposta de erro
     */
    public static class ErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private Map<String, String> details;
        private String path;

        private ErrorResponse() {}

        public static Builder builder() {
            return new Builder();
        }

        // Getters
        public LocalDateTime getTimestamp() { return timestamp; }
        public int getStatus() { return status; }
        public String getError() { return error; }
        public String getMessage() { return message; }
        public Map<String, String> getDetails() { return details; }
        public String getPath() { return path; }

        // Builder pattern
        public static class Builder {
            private ErrorResponse response = new ErrorResponse();

            public Builder timestamp(LocalDateTime timestamp) {
                response.timestamp = timestamp;
                return this;
            }

            public Builder status(int status) {
                response.status = status;
                return this;
            }

            public Builder error(String error) {
                response.error = error;
                return this;
            }

            public Builder message(String message) {
                response.message = message;
                return this;
            }

            public Builder details(Map<String, String> details) {
                response.details = details;
                return this;
            }

            public Builder path(String path) {
                response.path = path;
                return this;
            }

            public ErrorResponse build() {
                return response;
            }
        }
    }
}

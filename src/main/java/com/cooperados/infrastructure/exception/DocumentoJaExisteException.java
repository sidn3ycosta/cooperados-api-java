package com.cooperados.infrastructure.exception;

/**
 * Exceção lançada quando um documento (CPF/CNPJ) já existe no cadastro
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
public class DocumentoJaExisteException extends RuntimeException {

    public DocumentoJaExisteException(String message) {
        super(message);
    }

    public DocumentoJaExisteException(String message, Throwable cause) {
        super(message, cause);
    }
}

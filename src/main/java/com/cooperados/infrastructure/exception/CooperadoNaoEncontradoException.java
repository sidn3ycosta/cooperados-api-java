package com.cooperados.infrastructure.exception;

/**
 * Exceção lançada quando um cooperado não é encontrado
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
public class CooperadoNaoEncontradoException extends RuntimeException {

    public CooperadoNaoEncontradoException(String message) {
        super(message);
    }

    public CooperadoNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.cooperados.infrastructure.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a exceção CooperadoNaoEncontradoException
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@DisplayName("Exceção CooperadoNaoEncontradoException")
class CooperadoNaoEncontradoExceptionTest {

    @Test
    @DisplayName("Deve criar exceção com mensagem")
    void deveCriarExcecaoComMensagem() {
        String mensagem = "Cooperado não encontrado com ID: 123";
        CooperadoNaoEncontradoException exception = new CooperadoNaoEncontradoException(mensagem);

        assertEquals(mensagem, exception.getMessage());
    }

    @Test
    @DisplayName("Deve criar exceção com mensagem e causa")
    void deveCriarExcecaoComMensagemECausa() {
        String mensagem = "Cooperado não encontrado";
        RuntimeException causa = new RuntimeException("Erro interno");
        CooperadoNaoEncontradoException exception = new CooperadoNaoEncontradoException(mensagem, causa);

        assertEquals(mensagem, exception.getMessage());
        assertEquals(causa, exception.getCause());
    }

    @Test
    @DisplayName("Deve ser instância de RuntimeException")
    void deveSerInstanciaDeRuntimeException() {
        CooperadoNaoEncontradoException exception = new CooperadoNaoEncontradoException("Teste");
        
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    @DisplayName("Deve manter a stack trace")
    void deveManterAStackTrace() {
        CooperadoNaoEncontradoException exception = new CooperadoNaoEncontradoException("Teste");
        
        StackTraceElement[] stackTrace = exception.getStackTrace();
        assertNotNull(stackTrace);
        assertTrue(stackTrace.length > 0);
    }
}

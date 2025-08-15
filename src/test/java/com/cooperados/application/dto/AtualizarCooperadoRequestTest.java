package com.cooperados.application.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Testes unitários para o DTO AtualizarCooperadoRequest
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@DisplayName("DTO AtualizarCooperadoRequest")
class AtualizarCooperadoRequestTest {

    @Test
    @DisplayName("Deve criar request válido com todos os campos")
    void deveCriarRequestValidoComTodosOsCampos() {
        AtualizarCooperadoRequest request = new AtualizarCooperadoRequest(
            "João Silva Atualizado",
            LocalDate.of(1990, 5, 15),
            new BigDecimal("4000.00"),
            "11987654321",
            "joao.novo@email.com"
        );

        assertEquals("João Silva Atualizado", request.getNome());
        assertEquals(LocalDate.of(1990, 5, 15), request.getDataNascimentoConstituicao());
        assertEquals(new BigDecimal("4000.00"), request.getRendaFaturamento());
        assertEquals("11987654321", request.getTelefone());
        assertEquals("joao.novo@email.com", request.getEmail());
    }

    @Test
    @DisplayName("Deve criar request com construtor padrão")
    void deveCriarRequestComConstrutorPadrao() {
        AtualizarCooperadoRequest request = new AtualizarCooperadoRequest();
        
        request.setNome("Nome Atualizado");
        request.setDataNascimentoConstituicao(LocalDate.of(1985, 10, 20));
        request.setRendaFaturamento(new BigDecimal("5000.00"));
        request.setTelefone("11912345678");
        request.setEmail("novo@email.com");

        assertEquals("Nome Atualizado", request.getNome());
        assertEquals(LocalDate.of(1985, 10, 20), request.getDataNascimentoConstituicao());
        assertEquals(new BigDecimal("5000.00"), request.getRendaFaturamento());
        assertEquals("11912345678", request.getTelefone());
        assertEquals("novo@email.com", request.getEmail());
    }

    @Test
    @DisplayName("Deve verificar campos preenchidos corretamente")
    void deveVerificarCamposPreenchidosCorretamente() {
        AtualizarCooperadoRequest request = new AtualizarCooperadoRequest();
        
        // Inicialmente nenhum campo está preenchido
        assertFalse(request.hasNome());
        assertFalse(request.hasDataNascimentoConstituicao());
        assertFalse(request.hasRendaFaturamento());
        assertFalse(request.hasTelefone());
        assertFalse(request.hasEmail());
        assertFalse(request.hasAnyField());

        // Preenchendo campos
        request.setNome("Nome");
        assertTrue(request.hasNome());
        assertTrue(request.hasAnyField());

        request.setRendaFaturamento(new BigDecimal("1000.00"));
        assertTrue(request.hasRendaFaturamento());
        assertTrue(request.hasAnyField());

        request.setEmail("email@teste.com");
        assertTrue(request.hasEmail());
        assertTrue(request.hasAnyField());
    }

    @Test
    @DisplayName("Deve tratar campos nulos corretamente")
    void deveTratarCamposNulosCorretamente() {
        AtualizarCooperadoRequest request = new AtualizarCooperadoRequest();
        
        request.setNome(null);
        request.setEmail("");
        request.setTelefone("   ");

        assertFalse(request.hasNome());
        assertFalse(request.hasEmail());
        assertFalse(request.hasTelefone());
    }

    @Test
    @DisplayName("Deve gerar toString correto")
    void deveGerarToStringCorreto() {
        AtualizarCooperadoRequest request = new AtualizarCooperadoRequest(
            "Nome Teste",
            LocalDate.of(1990, 1, 1),
            new BigDecimal("3000.00"),
            "11987654321",
            "teste@email.com"
        );

        String toString = request.toString();
        
        assertTrue(toString.contains("Nome Teste"));
        assertTrue(toString.contains("1990-01-01"));
        assertTrue(toString.contains("3000.00"));
        assertTrue(toString.contains("11987654321"));
        assertTrue(toString.contains("teste@email.com"));
    }
}

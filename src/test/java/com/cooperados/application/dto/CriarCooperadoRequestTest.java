package com.cooperados.application.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Testes unitários para o DTO CriarCooperadoRequest
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@DisplayName("DTO CriarCooperadoRequest")
class CriarCooperadoRequestTest {

    @Test
    @DisplayName("Deve criar request válido com todos os campos")
    void deveCriarRequestValidoComTodosOsCampos() {
        CriarCooperadoRequest request = new CriarCooperadoRequest(
            "João Silva",
            "12345678909",
            LocalDate.of(1990, 5, 15),
            new BigDecimal("3500.00"),
            "11987654321",
            "joao@email.com"
        );

        assertEquals("João Silva", request.getNome());
        assertEquals("12345678909", request.getDocumento());
        assertEquals(LocalDate.of(1990, 5, 15), request.getDataNascimentoConstituicao());
        assertEquals(new BigDecimal("3500.00"), request.getRendaFaturamento());
        assertEquals("11987654321", request.getTelefone());
        assertEquals("joao@email.com", request.getEmail());
    }

    @Test
    @DisplayName("Deve criar request válido sem e-mail")
    void deveCriarRequestValidoSemEmail() {
        CriarCooperadoRequest request = new CriarCooperadoRequest(
            "Maria Santos",
            "98765432100",
            LocalDate.of(1985, 8, 20),
            new BigDecimal("4200.00"),
            "11912345678",
            null
        );

        assertEquals("Maria Santos", request.getNome());
        assertEquals("98765432100", request.getDocumento());
        assertNull(request.getEmail());
    }

    @Test
    @DisplayName("Deve criar request com CNPJ")
    void deveCriarRequestComCnpj() {
        CriarCooperadoRequest request = new CriarCooperadoRequest(
            "Empresa ABC Ltda",
            "12345678000195",
            LocalDate.of(2010, 3, 20),
            new BigDecimal("150000.00"),
            "11987654321",
            "contato@empresa.com"
        );

        assertEquals("Empresa ABC Ltda", request.getNome());
        assertEquals("12345678000195", request.getDocumento());
        assertEquals(new BigDecimal("150000.00"), request.getRendaFaturamento());
    }

    @Test
    @DisplayName("Deve usar construtor padrão")
    void deveUsarConstrutorPadrao() {
        CriarCooperadoRequest request = new CriarCooperadoRequest();
        
        request.setNome("Teste");
        request.setDocumento("12345678909");
        request.setDataNascimentoConstituicao(LocalDate.of(1990, 1, 1));
        request.setRendaFaturamento(new BigDecimal("1000.00"));
        request.setTelefone("11987654321");
        request.setEmail("teste@email.com");

        assertEquals("Teste", request.getNome());
        assertEquals("12345678909", request.getDocumento());
        assertEquals("teste@email.com", request.getEmail());
    }

    @Test
    @DisplayName("Deve gerar toString correto")
    void deveGerarToStringCorreto() {
        CriarCooperadoRequest request = new CriarCooperadoRequest(
            "João Silva",
            "12345678909",
            LocalDate.of(1990, 5, 15),
            new BigDecimal("3500.00"),
            "11987654321",
            "joao@email.com"
        );

        String toString = request.toString();
        
        assertTrue(toString.contains("João Silva"));
        assertTrue(toString.contains("12345678909"));
        assertTrue(toString.contains("joao@email.com"));
        assertTrue(toString.contains("3500.00"));
    }
}

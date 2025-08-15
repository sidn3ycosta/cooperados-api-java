package com.cooperados.shared.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para o validador de documento (CPF ou CNPJ)
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@DisplayName("Validador de Documento")
class DocumentoValidatorTest {

    private DocumentoValidator validator;

    @BeforeEach
    void setUp() {
        validator = new DocumentoValidator();
    }

    @Test
    @DisplayName("Deve validar CPF válido")
    void deveValidarCpfValido() {
        assertTrue(validator.isValid("12345678909", null));
        assertTrue(validator.isValid("98765432100", null));
        assertTrue(validator.isValid("123.456.789-09", null));
    }

    @Test
    @DisplayName("Deve validar CNPJ válido")
    void deveValidarCnpjValido() {
        assertTrue(validator.isValid("11222333000181", null));
        assertTrue(validator.isValid("11222333000181", null));
        assertTrue(validator.isValid("11.222.333/0001-81", null));
    }

    @Test
    @DisplayName("Deve rejeitar documento com tamanho incorreto")
    void deveRejeitarDocumentoComTamanhoIncorreto() {
        assertFalse(validator.isValid("1234567890", null));   // 10 dígitos
        assertFalse(validator.isValid("123456789012", null)); // 12 dígitos
        assertFalse(validator.isValid("1234567890123", null)); // 13 dígitos
        assertFalse(validator.isValid("123456789012345", null)); // 15 dígitos
    }

    @Test
    @DisplayName("Deve rejeitar documento nulo")
    void deveRejeitarDocumentoNulo() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    @DisplayName("Deve rejeitar documento vazio")
    void deveRejeitarDocumentoVazio() {
        assertFalse(validator.isValid("", null));
        assertFalse(validator.isValid("   ", null));
    }

    @Test
    @DisplayName("Deve identificar corretamente CPF")
    void deveIdentificarCorretamenteCpf() {
        assertTrue(DocumentoValidator.isCpf("12345678909"));
        assertTrue(DocumentoValidator.isCpf("123.456.789-09"));
        assertFalse(DocumentoValidator.isCpf("12345678000195"));
    }

    @Test
    @DisplayName("Deve identificar corretamente CNPJ")
    void deveIdentificarCorretamenteCnpj() {
        assertTrue(DocumentoValidator.isCnpj("11222333000181"));
        assertTrue(DocumentoValidator.isCnpj("11.222.333/0001-81"));
        assertFalse(DocumentoValidator.isCnpj("12345678909"));
    }

    @Test
    @DisplayName("Deve retornar tipo correto do documento")
    void deveRetornarTipoCorretoDoDocumento() {
        assertEquals("CPF", DocumentoValidator.getTipo("12345678909"));
        assertEquals("CNPJ", DocumentoValidator.getTipo("11222333000181"));
        assertEquals("INVALIDO", DocumentoValidator.getTipo("1234567890"));
    }
}

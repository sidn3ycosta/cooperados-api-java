package com.cooperados.shared.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para o validador de CPF
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@DisplayName("Validador de CPF")
class CpfValidatorTest {

    private CpfValidator validator;

    @BeforeEach
    void setUp() {
        validator = new CpfValidator();
    }

    @Test
    @DisplayName("Deve validar CPF válido")
    void deveValidarCpfValido() {
        // CPFs válidos conhecidos
        assertTrue(validator.isValid("52998224725", null));
        assertTrue(validator.isValid("11144477735", null));
        assertTrue(validator.isValid("12345678909", null));
    }

    @Test
    @DisplayName("Deve validar CPF com formatação")
    void deveValidarCpfComFormatacao() {
        assertTrue(validator.isValid("123.456.789-09", null));
        assertTrue(validator.isValid("529.982.247-25", null));
        assertTrue(validator.isValid("111.444.777-35", null));
    }

    @Test
    @DisplayName("Deve rejeitar CPF inválido")
    void deveRejeitarCpfInvalido() {
        // CPFs inválidos
        assertFalse(validator.isValid("12345678901", null));
        assertFalse(validator.isValid("98765432101", null));
        assertFalse(validator.isValid("11111111111", null));
    }

    @Test
    @DisplayName("Deve rejeitar CPF com dígitos iguais")
    void deveRejeitarCpfComDigitosIguais() {
        assertFalse(validator.isValid("11111111111", null));
        assertFalse(validator.isValid("22222222222", null));
        assertFalse(validator.isValid("99999999999", null));
    }

    @Test
    @DisplayName("Deve rejeitar CPF com tamanho incorreto")
    void deveRejeitarCpfComTamanhoIncorreto() {
        assertFalse(validator.isValid("1234567890", null));  // 10 dígitos
        assertFalse(validator.isValid("123456789012", null)); // 12 dígitos
        assertFalse(validator.isValid("", null)); // vazio
    }

    @Test
    @DisplayName("Deve rejeitar CPF nulo")
    void deveRejeitarCpfNulo() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    @DisplayName("Deve rejeitar CPF vazio")
    void deveRejeitarCpfVazio() {
        assertFalse(validator.isValid("", null));
        assertFalse(validator.isValid("   ", null));
    }

    @Test
    @DisplayName("Deve validar CPFs de casos extremos")
    void deveValidarCpfsCasosExtremos() {
        // CPFs válidos com dígitos verificadores 0
        assertTrue(validator.isValid("12345678909", null));
        assertTrue(validator.isValid("52998224725", null));
    }
}

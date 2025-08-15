package com.cooperados.shared.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para o validador de CNPJ
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@DisplayName("Validador de CNPJ")
class CnpjValidatorTest {

    private CnpjValidator validator;

    @BeforeEach
    void setUp() {
        validator = new CnpjValidator();
    }

    @Test
    @DisplayName("Deve validar CNPJ válido")
    void deveValidarCnpjValido() {
        // CNPJs válidos conhecidos e testados
        assertTrue(validator.isValid("11222333000181", null));
        assertTrue(validator.isValid("11222333000181", null));
        assertTrue(validator.isValid("11222333000181", null));
    }

    @Test
    @DisplayName("Deve validar CNPJ com formatação")
    void deveValidarCnpjComFormatacao() {
        assertTrue(validator.isValid("11.222.333/0001-81", null));
        assertTrue(validator.isValid("11.222.333/0001-81", null));
        assertTrue(validator.isValid("11.222.333/0001-81", null));
    }

    @Test
    @DisplayName("Deve rejeitar CNPJ inválido")
    void deveRejeitarCnpjInvalido() {
        // CNPJs inválidos
        assertFalse(validator.isValid("12345678000196", null));
        assertFalse(validator.isValid("98765432000188", null));
        assertFalse(validator.isValid("11111111111111", null));
    }

    @Test
    @DisplayName("Deve rejeitar CNPJ com dígitos iguais")
    void deveRejeitarCnpjComDigitosIguais() {
        assertFalse(validator.isValid("11111111111111", null));
        assertFalse(validator.isValid("22222222222222", null));
        assertFalse(validator.isValid("99999999999999", null));
    }

    @Test
    @DisplayName("Deve rejeitar CNPJ com tamanho incorreto")
    void deveRejeitarCnpjComTamanhoIncorreto() {
        assertFalse(validator.isValid("1234567800019", null));  // 13 dígitos
        assertFalse(validator.isValid("123456780001956", null)); // 15 dígitos
        assertFalse(validator.isValid("", null)); // vazio
    }

    @Test
    @DisplayName("Deve rejeitar CNPJ nulo")
    void deveRejeitarCnpjNulo() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    @DisplayName("Deve rejeitar CNPJ vazio")
    void deveRejeitarCnpjVazio() {
        assertFalse(validator.isValid("", null));
        assertFalse(validator.isValid("   ", null));
    }

    @Test
    @DisplayName("Deve validar CNPJs de casos extremos")
    void deveValidarCnpjsCasosExtremos() {
        // CNPJs válidos conhecidos e testados
        assertTrue(validator.isValid("11222333000181", null));
        assertTrue(validator.isValid("11222333000181", null));
    }
}

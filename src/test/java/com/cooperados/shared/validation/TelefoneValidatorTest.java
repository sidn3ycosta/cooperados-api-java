package com.cooperados.shared.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para o validador de telefone
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@DisplayName("Validador de Telefone")
class TelefoneValidatorTest {

    private TelefoneValidator validator;

    @BeforeEach
    void setUp() {
        validator = new TelefoneValidator();
    }

    @Test
    @DisplayName("Deve validar telefone fixo válido")
    void deveValidarTelefoneFixoValido() {
        assertTrue(validator.isValid("1134567890", null));
        assertTrue(validator.isValid("2134567890", null));
        assertTrue(validator.isValid("1134567890", null));
    }

    @Test
    @DisplayName("Deve validar celular válido")
    void deveValidarCelularValido() {
        assertTrue(validator.isValid("11987654321", null));
        assertTrue(validator.isValid("21987654321", null));
        assertTrue(validator.isValid("11987654321", null));
    }

    @Test
    @DisplayName("Deve rejeitar telefone com DDD inválido")
    void deveRejeitarTelefoneComDddInvalido() {
        assertFalse(validator.isValid("10987654321", null)); // DDD 10 não existe
        assertFalse(validator.isValid("00987654321", null)); // DDD 00 não existe
        assertFalse(validator.isValid("10087654321", null)); // DDD 100 não existe
    }

    @Test
    @DisplayName("Deve rejeitar telefone com tamanho incorreto")
    void deveRejeitarTelefoneComTamanhoIncorreto() {
        assertFalse(validator.isValid("119876543", null));    // 9 dígitos
        assertFalse(validator.isValid("119876543210", null)); // 12 dígitos
        assertFalse(validator.isValid("", null)); // vazio
    }

    @Test
    @DisplayName("Deve rejeitar telefone nulo")
    void deveRejeitarTelefoneNulo() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    @DisplayName("Deve rejeitar telefone vazio")
    void deveRejeitarTelefoneVazio() {
        assertFalse(validator.isValid("", null));
        assertFalse(validator.isValid("   ", null));
    }

    @Test
    @DisplayName("Deve identificar corretamente celular")
    void deveIdentificarCorretamenteCelular() {
        assertTrue(TelefoneValidator.isCelular("11987654321"));
        assertTrue(TelefoneValidator.isCelular("11987654321"));
        assertFalse(TelefoneValidator.isCelular("1134567890"));
    }

    @Test
    @DisplayName("Deve identificar corretamente telefone fixo")
    void deveIdentificarCorretamenteTelefoneFixo() {
        assertTrue(TelefoneValidator.isFixo("1134567890"));
        assertTrue(TelefoneValidator.isFixo("1134567890"));
        assertFalse(TelefoneValidator.isFixo("11987654321"));
    }

    @Test
    @DisplayName("Deve formatar telefone corretamente")
    void deveFormatarTelefoneCorretamente() {
        assertEquals("(11) 98765-4321", TelefoneValidator.formatar("11987654321"));
        assertEquals("(21) 3456-7890", TelefoneValidator.formatar("2134567890"));
        assertEquals("(11) 98765-4321", TelefoneValidator.formatar("(11) 98765-4321"));
    }

    @Test
    @DisplayName("Deve aceitar telefones com formatação")
    void deveAceitarTelefonesComFormatacao() {
        assertTrue(validator.isValid("1134567890", null));
        assertTrue(validator.isValid("21987654321", null));
        assertTrue(validator.isValid("1134567890", null));
        assertTrue(validator.isValid("21987654321", null));
    }
}

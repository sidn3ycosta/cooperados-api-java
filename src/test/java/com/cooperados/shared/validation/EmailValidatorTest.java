package com.cooperados.shared.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para o validador de e-mail
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@DisplayName("Validador de E-mail")
class EmailValidatorTest {

    private EmailValidator validator;

    @BeforeEach
    void setUp() {
        validator = new EmailValidator();
    }

    @Test
    @DisplayName("Deve validar e-mail válido")
    void deveValidarEmailValido() {
        assertTrue(validator.isValid("usuario@exemplo.com", null));
        assertTrue(validator.isValid("nome.sobrenome@empresa.com.br", null));
        assertTrue(validator.isValid("user123@domain.org", null));
    }

    @Test
    @DisplayName("Deve aceitar e-mail nulo (opcional)")
    void deveAceitarEmailNulo() {
        assertTrue(validator.isValid(null, null));
    }

    @Test
    @DisplayName("Deve aceitar e-mail vazio (opcional)")
    void deveAceitarEmailVazio() {
        assertTrue(validator.isValid("", null));
        assertTrue(validator.isValid("   ", null));
    }

    @Test
    @DisplayName("Deve rejeitar e-mail sem @")
    void deveRejeitarEmailSemArroba() {
        assertFalse(validator.isValid("usuario.exemplo.com", null));
        assertFalse(validator.isValid("usuario", null));
    }

    @Test
    @DisplayName("Deve rejeitar e-mail com parte local vazia")
    void deveRejeitarEmailComParteLocalVazia() {
        assertFalse(validator.isValid("@exemplo.com", null));
        assertFalse(validator.isValid("..@exemplo.com", null));
    }

    @Test
    @DisplayName("Deve rejeitar e-mail com domínio vazio")
    void deveRejeitarEmailComDominioVazio() {
        assertFalse(validator.isValid("usuario@", null));
        assertFalse(validator.isValid("usuario@.", null));
    }

    @Test
    @DisplayName("Deve rejeitar e-mail com domínio inválido")
    void deveRejeitarEmailComDominioInvalido() {
        assertFalse(validator.isValid("usuario@.com", null));
        assertFalse(validator.isValid("usuario@exemplo.", null));
        assertFalse(validator.isValid("usuario@exemplo..com", null));
    }

    @Test
    @DisplayName("Deve rejeitar e-mail muito longo")
    void deveRejeitarEmailMuitoLongo() {
        String emailLongo = "a".repeat(65) + "@exemplo.com";
        assertFalse(validator.isValid(emailLongo, null));
    }

    @Test
    @DisplayName("Deve validar e-mails com caracteres especiais")
    void deveValidarEmailsComCaracteresEspeciais() {
        assertTrue(validator.isValid("user+tag@exemplo.com", null));
        assertTrue(validator.isValid("user.name@exemplo.com", null));
        assertTrue(validator.isValid("user_name@exemplo.com", null));
    }

    @Test
    @DisplayName("Deve validar domínio corretamente")
    void deveValidarDominioCorretamente() {
        assertTrue(EmailValidator.isDomainValid("usuario@exemplo.com"));
        assertTrue(EmailValidator.isDomainValid("usuario@sub.exemplo.com"));
        assertFalse(EmailValidator.isDomainValid("usuario@.com"));
        assertFalse(EmailValidator.isDomainValid("usuario@exemplo."));
        assertFalse(EmailValidator.isDomainValid("usuario@exemplo..com"));
    }

    @Test
    @DisplayName("Deve formatar e-mail corretamente")
    void deveFormatarEmailCorretamente() {
        assertEquals("usuario@exemplo.com", EmailValidator.formatar("Usuario@Exemplo.COM"));
        assertEquals("nome.sobrenome@empresa.com.br", EmailValidator.formatar("  Nome.Sobrenome@Empresa.COM.BR  "));
    }
}

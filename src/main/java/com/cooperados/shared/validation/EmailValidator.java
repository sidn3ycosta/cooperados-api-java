package com.cooperados.shared.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Validador customizado para e-mail
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    // Padrão RFC 5322 para validação de e-mail
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$"
    );

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        // Inicialização não necessária
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return true; // E-mail é opcional
        }

        String emailTrimmed = email.trim();
        
        // Verifica se está vazio
        if (emailTrimmed.isEmpty()) {
            return true; // E-mail vazio é válido (opcional)
        }

        // Verifica o tamanho máximo
        if (emailTrimmed.length() > 254) { // RFC 5321 limit
            return false;
        }

        // Verifica se contém @
        if (!emailTrimmed.contains("@")) {
            return false;
        }

        // Verifica se tem pelo menos um caractere antes e depois do @
        String[] parts = emailTrimmed.split("@");
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            return false;
        }

        // Verifica se a parte local não excede 64 caracteres
        if (parts[0].length() > 64) {
            return false;
        }

        // Verifica se o domínio não excede 253 caracteres
        if (parts[1].length() > 253) {
            return false;
        }

        // Verifica se a parte local não contém apenas pontos ou caracteres inválidos
        if (parts[0].matches("^[.]*$") || parts[0].startsWith(".") || parts[0].endsWith(".")) {
            return false;
        }

        // Validação com regex
        return EMAIL_PATTERN.matcher(emailTrimmed).matches();
    }

    /**
     * Verifica se o domínio do e-mail é válido
     */
    public static boolean isDomainValid(String email) {
        if (email == null || !email.contains("@")) {
            return false;
        }

        String domain = email.substring(email.indexOf("@") + 1);
        
        // Verifica se o domínio tem pelo menos um ponto
        if (!domain.contains(".")) {
            return false;
        }

        // Verifica se não começa ou termina com ponto
        if (domain.startsWith(".") || domain.endsWith(".")) {
            return false;
        }

        // Verifica se não tem pontos consecutivos
        if (domain.contains("..")) {
            return false;
        }

        return true;
    }

    /**
     * Formata o e-mail para exibição (converte para minúsculas)
     */
    public static String formatar(String email) {
        if (email == null) return null;
        return email.trim().toLowerCase();
    }
}

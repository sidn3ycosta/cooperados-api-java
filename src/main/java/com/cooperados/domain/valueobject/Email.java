package com.cooperados.domain.valueobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Objects;

/**
 * Value Object para Email com validação de formato
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Embeddable
public class Email {

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Formato de e-mail inválido")
    @Size(max = 255, message = "E-mail deve ter no máximo 255 caracteres")
    @Column(name = "email", length = 255)
    private String valor;

    protected Email() {
        // Construtor protegido para JPA
    }

    public Email(String valor) {
        this.valor = valor != null ? valor.trim() : null;
        if (valor != null) {
            validarEmail();
        }
    }

    public String getValor() {
        return valor;
    }

    public boolean isPresent() {
        return valor != null && !valor.trim().isEmpty();
    }

    private void validarEmail() {
        if (valor == null || valor.trim().isEmpty()) {
            return; // Email é opcional
        }

        // Regex mais robusto para validação de email
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!java.util.regex.Pattern.matches(emailRegex, valor)) {
            throw new IllegalArgumentException("Formato de e-mail inválido");
        }

        // Validações adicionais
        if (valor.length() > 255) {
            throw new IllegalArgumentException("E-mail deve ter no máximo 255 caracteres");
        }

        if (valor.startsWith(".") || valor.endsWith(".")) {
            throw new IllegalArgumentException("E-mail não pode começar ou terminar com ponto");
        }

        if (valor.contains("..")) {
            throw new IllegalArgumentException("E-mail não pode conter pontos consecutivos");
        }

        // Validação do domínio
        String[] parts = valor.split("@");
        if (parts.length != 2) {
            throw new IllegalArgumentException("E-mail deve conter exatamente um @");
        }

        String domain = parts[1];
        if (domain.startsWith(".") || domain.endsWith(".")) {
            throw new IllegalArgumentException("Domínio do e-mail não pode começar ou terminar com ponto");
        }

        if (domain.contains("..")) {
            throw new IllegalArgumentException("Domínio do e-mail não pode conter pontos consecutivos");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(valor, email.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor;
    }
}

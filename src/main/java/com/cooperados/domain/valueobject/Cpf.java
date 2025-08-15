package com.cooperados.domain.valueobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Value Object para CPF com validação algorítmica
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Embeddable
public class Cpf {

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "^\\d{11}$", message = "CPF deve conter exatamente 11 dígitos")
    @Column(name = "documento_cpf", length = 11, nullable = false)
    private String valor;

    protected Cpf() {
        // Construtor protegido para JPA
    }

    public Cpf(String valor) {
        this.valor = limparCpf(valor);
        validarCpf();
    }

    public String getValor() {
        return valor;
    }

    public String getValorFormatado() {
        if (valor == null || valor.length() != 11) {
            return valor;
        }
        return valor.substring(0, 3) + "." + 
               valor.substring(3, 6) + "." + 
               valor.substring(6, 9) + "-" + 
               valor.substring(9, 11);
    }

    private String limparCpf(String cpf) {
        if (cpf == null) {
            return null;
        }
        return cpf.replaceAll("[^\\d]", "");
    }

    private void validarCpf() {
        if (valor == null || valor.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter exatamente 11 dígitos");
        }

        // Verifica se todos os dígitos são iguais
        if (valor.chars().distinct().count() == 1) {
            throw new IllegalArgumentException("CPF não pode ter todos os dígitos iguais");
        }

        // Validação dos dígitos verificadores
        if (!validarDigitosVerificadores()) {
            throw new IllegalArgumentException("CPF inválido - dígitos verificadores incorretos");
        }
    }

    private boolean validarDigitosVerificadores() {
        // Primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(valor.charAt(i)) * (10 - i);
        }
        int resto = soma % 11;
        int primeiroDigito = resto < 2 ? 0 : 11 - resto;

        if (Character.getNumericValue(valor.charAt(9)) != primeiroDigito) {
            return false;
        }

        // Segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(valor.charAt(i)) * (11 - i);
        }
        resto = soma % 11;
        int segundoDigito = resto < 2 ? 0 : 11 - resto;

        return Character.getNumericValue(valor.charAt(10)) == segundoDigito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cpf cpf = (Cpf) o;
        return Objects.equals(valor, cpf.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return getValorFormatado();
    }
}

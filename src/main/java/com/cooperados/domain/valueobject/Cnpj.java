package com.cooperados.domain.valueobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Value Object para CNPJ com validação algorítmica
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Embeddable
public class Cnpj {

    @NotBlank(message = "CNPJ é obrigatório")
    @Pattern(regexp = "^\\d{14}$", message = "CNPJ deve conter exatamente 14 dígitos")
    @Column(name = "documento_cnpj", length = 14, nullable = false)
    private String valor;

    protected Cnpj() {
        // Construtor protegido para JPA
    }

    public Cnpj(String valor) {
        this.valor = limparCnpj(valor);
        validarCnpj();
    }

    public String getValor() {
        return valor;
    }

    public String getValorFormatado() {
        if (valor == null || valor.length() != 14) {
            return valor;
        }
        return valor.substring(0, 2) + "." + 
               valor.substring(2, 5) + "." + 
               valor.substring(5, 8) + "/" + 
               valor.substring(8, 12) + "-" + 
               valor.substring(12, 14);
    }

    private String limparCnpj(String cnpj) {
        if (cnpj == null) {
            return null;
        }
        return cnpj.replaceAll("[^\\d]", "");
    }

    private void validarCnpj() {
        if (valor == null || valor.length() != 14) {
            throw new IllegalArgumentException("CNPJ deve conter exatamente 14 dígitos");
        }

        // Verifica se todos os dígitos são iguais
        if (valor.chars().distinct().count() == 1) {
            throw new IllegalArgumentException("CNPJ não pode ter todos os dígitos iguais");
        }

        // Validação dos dígitos verificadores
        if (!validarDigitosVerificadores()) {
            throw new IllegalArgumentException("CNPJ inválido - dígitos verificadores incorretos");
        }
    }

    private boolean validarDigitosVerificadores() {
        // Primeiro dígito verificador
        int[] multiplicadores1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(valor.charAt(i)) * multiplicadores1[i];
        }
        int resto = soma % 11;
        int primeiroDigito = resto < 2 ? 0 : 11 - resto;

        if (Character.getNumericValue(valor.charAt(12)) != primeiroDigito) {
            return false;
        }

        // Segundo dígito verificador
        int[] multiplicadores2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(valor.charAt(i)) * multiplicadores2[i];
        }
        resto = soma % 11;
        int segundoDigito = resto < 2 ? 0 : 11 - resto;

        return Character.getNumericValue(valor.charAt(13)) == segundoDigito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cnpj cnpj = (Cnpj) o;
        return Objects.equals(valor, cnpj.valor);
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

package com.cooperados.domain.valueobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Value Object para Telefone com validação de formato brasileiro
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Embeddable
public class Telefone {

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "^\\d{10,11}$", message = "Telefone deve conter 10 ou 11 dígitos")
    @Column(name = "telefone", length = 11, nullable = false)
    private String valor;

    protected Telefone() {
        // Construtor protegido para JPA
    }

    public Telefone(String valor) {
        this.valor = limparTelefone(valor);
        validarTelefone();
    }

    public String getValor() {
        return valor;
    }

    public String getValorFormatado() {
        if (valor == null) {
            return null;
        }
        
        if (valor.length() == 10) {
            // Telefone fixo: (11) 1234-5678
            return "(" + valor.substring(0, 2) + ") " + 
                   valor.substring(2, 6) + "-" + 
                   valor.substring(6, 10);
        } else if (valor.length() == 11) {
            // Celular: (11) 91234-5678
            return "(" + valor.substring(0, 2) + ") " + 
                   valor.substring(2, 7) + "-" + 
                   valor.substring(7, 11);
        }
        
        return valor;
    }

    private String limparTelefone(String telefone) {
        if (telefone == null) {
            return null;
        }
        return telefone.replaceAll("[^\\d]", "");
    }

    private void validarTelefone() {
        if (valor == null || (valor.length() != 10 && valor.length() != 11)) {
            throw new IllegalArgumentException("Telefone deve conter 10 ou 11 dígitos");
        }

        // Validação do DDD (deve estar entre 11 e 99)
        String ddd = valor.substring(0, 2);
        int dddNumero = Integer.parseInt(ddd);
        if (dddNumero < 11 || dddNumero > 99) {
            throw new IllegalArgumentException("DDD inválido. Deve estar entre 11 e 99");
        }

        // Validação específica para celular (11 dígitos)
        if (valor.length() == 11) {
            // Primeiro dígito após DDD deve ser 9 para celular
            if (valor.charAt(2) != '9') {
                throw new IllegalArgumentException("Celular deve começar com 9 após o DDD");
            }
        }
    }

    public boolean isCelular() {
        return valor != null && valor.length() == 11;
    }

    public boolean isFixo() {
        return valor != null && valor.length() == 10;
    }

    public String getDdd() {
        return valor != null && valor.length() >= 2 ? valor.substring(0, 2) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return Objects.equals(valor, telefone.valor);
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

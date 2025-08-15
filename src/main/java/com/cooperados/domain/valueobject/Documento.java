package com.cooperados.domain.valueobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Value Object para Documento que pode ser CPF ou CNPJ
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Embeddable
public class Documento {

    @NotNull(message = "Documento é obrigatório")
    @Column(name = "tipo_documento", length = 4, nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipo;

    @Embedded
    private Cpf cpf;

    @Embedded
    private Cnpj cnpj;

    protected Documento() {
        // Construtor protegido para JPA
    }

    public Documento(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("Documento é obrigatório");
        }

        String documentoLimpo = valor.replaceAll("[^\\d]", "");
        
        if (documentoLimpo.length() == 11) {
            this.tipo = TipoDocumento.CPF;
            this.cpf = new Cpf(documentoLimpo);
            this.cnpj = null;
        } else if (documentoLimpo.length() == 14) {
            this.tipo = TipoDocumento.CNPJ;
            this.cnpj = new Cnpj(documentoLimpo);
            this.cpf = null;
        } else {
            throw new IllegalArgumentException("Documento deve ter 11 dígitos (CPF) ou 14 dígitos (CNPJ)");
        }
    }

    public static Documento cpf(String valor) {
        return new Documento(valor);
    }

    public static Documento cnpj(String valor) {
        return new Documento(valor);
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    public String getValor() {
        if (tipo == TipoDocumento.CPF) {
            return cpf.getValor();
        } else {
            return cnpj.getValor();
        }
    }

    public String getValorFormatado() {
        if (tipo == TipoDocumento.CPF) {
            return cpf.getValorFormatado();
        } else {
            return cnpj.getValorFormatado();
        }
    }

    public boolean isCpf() {
        return tipo == TipoDocumento.CPF;
    }

    public boolean isCnpj() {
        return tipo == TipoDocumento.CNPJ;
    }

    public Cpf getCpf() {
        if (tipo != TipoDocumento.CPF) {
            throw new IllegalStateException("Este documento não é um CPF");
        }
        return cpf;
    }

    public Cnpj getCnpj() {
        if (tipo != TipoDocumento.CNPJ) {
            throw new IllegalStateException("Este documento não é um CNPJ");
        }
        return cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documento documento = (Documento) o;
        return tipo == documento.tipo && 
               Objects.equals(cpf, documento.cpf) && 
               Objects.equals(cnpj, documento.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, cpf, cnpj);
    }

    @Override
    public String toString() {
        return getValorFormatado();
    }

    /**
     * Enum para tipos de documento
     */
    public enum TipoDocumento {
        CPF("CPF"),
        CNPJ("CNPJ");

        private final String descricao;

        TipoDocumento(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }
}

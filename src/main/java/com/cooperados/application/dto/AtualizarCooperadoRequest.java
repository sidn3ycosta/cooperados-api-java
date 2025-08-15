package com.cooperados.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO para atualização de cooperado
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
public class AtualizarCooperadoRequest {

    @Size(min = 2, max = 255, message = "Nome deve ter entre 2 e 255 caracteres")
    private String nome;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Data de nascimento/constituição não pode ser futura")
    private LocalDate dataNascimentoConstituicao;

    @DecimalMin(value = "0.01", message = "Renda/faturamento deve ser maior que zero")
    @DecimalMax(value = "999999999999.99", message = "Renda/faturamento muito alto")
    @Digits(integer = 12, fraction = 2, message = "Renda/faturamento deve ter no máximo 12 dígitos inteiros e 2 decimais")
    private BigDecimal rendaFaturamento;

    @Pattern(regexp = "^\\d{10,11}$", message = "Telefone deve conter 10 ou 11 dígitos")
    private String telefone;

    @Email(message = "Formato de e-mail inválido")
    @Size(max = 255, message = "E-mail deve ter no máximo 255 caracteres")
    private String email;

    // Construtores
    public AtualizarCooperadoRequest() {
        // Construtor padrão para deserialização JSON
    }

    public AtualizarCooperadoRequest(String nome, LocalDate dataNascimentoConstituicao,
                                   BigDecimal rendaFaturamento, String telefone, String email) {
        this.nome = nome;
        this.dataNascimentoConstituicao = dataNascimentoConstituicao;
        this.rendaFaturamento = rendaFaturamento;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimentoConstituicao() {
        return dataNascimentoConstituicao;
    }

    public void setDataNascimentoConstituicao(LocalDate dataNascimentoConstituicao) {
        this.dataNascimentoConstituicao = dataNascimentoConstituicao;
    }

    public BigDecimal getRendaFaturamento() {
        return rendaFaturamento;
    }

    public void setRendaFaturamento(BigDecimal rendaFaturamento) {
        this.rendaFaturamento = rendaFaturamento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Métodos de validação
    public boolean hasNome() {
        return nome != null && !nome.trim().isEmpty();
    }

    public boolean hasDataNascimentoConstituicao() {
        return dataNascimentoConstituicao != null;
    }

    public boolean hasRendaFaturamento() {
        return rendaFaturamento != null;
    }

    public boolean hasTelefone() {
        return telefone != null && !telefone.trim().isEmpty();
    }

    public boolean hasEmail() {
        return email != null && !email.trim().isEmpty();
    }

    public boolean hasAnyField() {
        return hasNome() || hasDataNascimentoConstituicao() || hasRendaFaturamento() || 
               hasTelefone() || hasEmail();
    }

    @Override
    public String toString() {
        return "AtualizarCooperadoRequest{" +
                "nome='" + nome + '\'' +
                ", dataNascimentoConstituicao=" + dataNascimentoConstituicao +
                ", rendaFaturamento=" + rendaFaturamento +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

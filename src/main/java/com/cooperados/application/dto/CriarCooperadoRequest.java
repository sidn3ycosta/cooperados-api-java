package com.cooperados.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import com.cooperados.shared.validation.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO para criação de cooperado
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
public class CriarCooperadoRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 255, message = "Nome deve ter entre 2 e 255 caracteres")
    private String nome;

    @NotBlank(message = "Documento (CPF/CNPJ) é obrigatório")
    @ValidDocumento(message = "Documento deve ser um CPF ou CNPJ válido")
    private String documento;

    @NotNull(message = "Data de nascimento/constituição é obrigatória")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Data de nascimento/constituição não pode ser futura")
    private LocalDate dataNascimentoConstituicao;

    @NotNull(message = "Renda/faturamento é obrigatório")
    @DecimalMin(value = "0.01", message = "Renda/faturamento deve ser maior que zero")
    @DecimalMax(value = "999999999999.99", message = "Renda/faturamento muito alto")
    @Digits(integer = 12, fraction = 2, message = "Renda/faturamento deve ter no máximo 12 dígitos inteiros e 2 decimais")
    private BigDecimal rendaFaturamento;

    @NotBlank(message = "Telefone é obrigatório")
    @ValidTelefone(message = "Telefone deve ser um número válido brasileiro")
    private String telefone;

    @ValidEmail(message = "Formato de e-mail inválido")
    private String email;

    // Construtores
    public CriarCooperadoRequest() {
        // Construtor padrão para deserialização JSON
    }

    public CriarCooperadoRequest(String nome, String documento, LocalDate dataNascimentoConstituicao,
                                BigDecimal rendaFaturamento, String telefone, String email) {
        this.nome = nome;
        this.documento = documento;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    @Override
    public String toString() {
        return "CriarCooperadoRequest{" +
                "nome='" + nome + '\'' +
                ", documento='" + documento + '\'' +
                ", dataNascimentoConstituicao=" + dataNascimentoConstituicao +
                ", rendaFaturamento=" + rendaFaturamento +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

package com.cooperados.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO de resposta para listagem de cooperados
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CooperadoListResponse {

    private Long id;
    private String nome;
    private String tipoDocumento;
    private String documentoFormatado;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimentoConstituicao;
    
    private BigDecimal rendaFaturamento;
    private String telefoneFormatado;
    private String email;
    private boolean ativo;

    // Construtores
    public CooperadoListResponse() {
        // Construtor padrão para serialização JSON
    }

    public CooperadoListResponse(Long id, String nome, String tipoDocumento, String documentoFormatado,
                               LocalDate dataNascimentoConstituicao, BigDecimal rendaFaturamento,
                               String telefoneFormatado, String email, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.tipoDocumento = tipoDocumento;
        this.documentoFormatado = documentoFormatado;
        this.dataNascimentoConstituicao = dataNascimentoConstituicao;
        this.rendaFaturamento = rendaFaturamento;
        this.telefoneFormatado = telefoneFormatado;
        this.email = email;
        this.ativo = ativo;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumentoFormatado() {
        return documentoFormatado;
    }

    public void setDocumentoFormatado(String documentoFormatado) {
        this.documentoFormatado = documentoFormatado;
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

    public String getTelefoneFormatado() {
        return telefoneFormatado;
    }

    public void setTelefoneFormatado(String telefoneFormatado) {
        this.telefoneFormatado = telefoneFormatado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "CooperadoListResponse{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", documentoFormatado='" + documentoFormatado + '\'' +
                ", dataNascimentoConstituicao=" + dataNascimentoConstituicao +
                ", rendaFaturamento=" + rendaFaturamento +
                ", telefoneFormatado='" + telefoneFormatado + '\'' +
                ", email='" + email + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}

package com.cooperados.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO de resposta para cooperado
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CooperadoResponse {

    private Long id;
    private String nome;
    private String documento;
    private String tipoDocumento;
    private String documentoFormatado;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimentoConstituicao;
    
    private BigDecimal rendaFaturamento;
    private String telefone;
    private String telefoneFormatado;
    private String email;
    private boolean ativo;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataCriacao;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataAtualizacao;
    
    private Long versao;

    // Construtores
    public CooperadoResponse() {
        // Construtor padrão para serialização JSON
    }

    public CooperadoResponse(Long id, String nome, String documento, String tipoDocumento,
                           String documentoFormatado, LocalDate dataNascimentoConstituicao,
                           BigDecimal rendaFaturamento, String telefone, String telefoneFormatado,
                           String email, boolean ativo, LocalDateTime dataCriacao,
                           LocalDateTime dataAtualizacao, Long versao) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.tipoDocumento = tipoDocumento;
        this.documentoFormatado = documentoFormatado;
        this.dataNascimentoConstituicao = dataNascimentoConstituicao;
        this.rendaFaturamento = rendaFaturamento;
        this.telefone = telefone;
        this.telefoneFormatado = telefoneFormatado;
        this.email = email;
        this.ativo = ativo;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.versao = versao;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getVersao() {
        return versao;
    }

    public void setVersao(Long versao) {
        this.versao = versao;
    }

    @Override
    public String toString() {
        return "CooperadoResponse{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", documento='" + documento + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", documentoFormatado='" + documentoFormatado + '\'' +
                ", dataNascimentoConstituicao=" + dataNascimentoConstituicao +
                ", rendaFaturamento=" + rendaFaturamento +
                ", telefone='" + telefone + '\'' +
                ", telefoneFormatado='" + telefoneFormatado + '\'' +
                ", email='" + email + '\'' +
                ", ativo=" + ativo +
                ", dataCriacao=" + dataCriacao +
                ", dataAtualizacao=" + dataAtualizacao +
                ", versao=" + versao +
                '}';
    }
}

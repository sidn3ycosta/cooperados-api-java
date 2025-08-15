package com.cooperados.domain.entity;

import com.cooperados.domain.valueobject.Documento;
import com.cooperados.domain.valueobject.Email;
import com.cooperados.domain.valueobject.Telefone;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidade de domínio Cooperado
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Entity
@Table(name = "cooperados", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"documento_cpf"}, name = "uk_cooperados_cpf"),
           @UniqueConstraint(columnNames = {"documento_cnpj"}, name = "uk_cooperados_cnpj")
       })
@EntityListeners(AuditingEntityListener.class)
public class Cooperado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 255, message = "Nome deve ter entre 2 e 255 caracteres")
    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @NotNull(message = "Documento é obrigatório")
    @Embedded
    private Documento documento;

    @NotNull(message = "Data de nascimento/constituição é obrigatória")
    @Column(name = "data_nascimento_constituicao", nullable = false)
    private LocalDate dataNascimentoConstituicao;

    @NotNull(message = "Renda/faturamento é obrigatório")
    @Positive(message = "Renda/faturamento deve ser positivo")
    @Column(name = "renda_faturamento", precision = 15, scale = 2, nullable = false)
    private BigDecimal rendaFaturamento;

    @NotNull(message = "Telefone é obrigatório")
    @Embedded
    private Telefone telefone;

    @Embedded
    private Email email;

    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    @CreatedDate
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Version
    @Column(name = "versao")
    private Long versao;

    protected Cooperado() {
        // Construtor protegido para JPA
    }

    public Cooperado(String nome, Documento documento, LocalDate dataNascimentoConstituicao,
                    BigDecimal rendaFaturamento, Telefone telefone, Email email) {
        this.nome = nome;
        this.documento = documento;
        this.dataNascimentoConstituicao = dataNascimentoConstituicao;
        this.rendaFaturamento = rendaFaturamento;
        this.telefone = telefone;
        this.email = email;
        this.ativo = true;
    }

    // Métodos de negócio
    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    public void atualizarNome(String novoNome) {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        this.nome = novoNome.trim();
    }

    public void atualizarRendaFaturamento(BigDecimal novaRenda) {
        if (novaRenda == null || novaRenda.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Renda/faturamento deve ser positivo");
        }
        this.rendaFaturamento = novaRenda;
    }

    public void atualizarTelefone(Telefone novoTelefone) {
        if (novoTelefone == null) {
            throw new IllegalArgumentException("Telefone não pode ser nulo");
        }
        this.telefone = novoTelefone;
    }

    public void atualizarEmail(Email novoEmail) {
        this.email = novoEmail; // Email pode ser nulo (opcional)
    }

    public void atualizarDataNascimentoConstituicao(LocalDate novaData) {
        if (novaData == null) {
            throw new IllegalArgumentException("Data de nascimento/constituição não pode ser nula");
        }
        if (novaData.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento/constituição não pode ser futura");
        }
        this.dataNascimentoConstituicao = novaData;
    }

    // Setters para compatibilidade com JPA
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimentoConstituicao(LocalDate dataNascimentoConstituicao) {
        this.dataNascimentoConstituicao = dataNascimentoConstituicao;
    }

    public void setRendaFaturamento(BigDecimal rendaFaturamento) {
        this.rendaFaturamento = rendaFaturamento;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    // Métodos de remoção e reativação
    public void remover() {
        this.ativo = false;
    }

    public void reativar() {
        this.ativo = true;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Documento getDocumento() {
        return documento;
    }

    public LocalDate getDataNascimentoConstituicao() {
        return dataNascimentoConstituicao;
    }

    public BigDecimal getRendaFaturamento() {
        return rendaFaturamento;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public Email getEmail() {
        return email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public Long getVersao() {
        return versao;
    }

    // Métodos de validação de negócio
    public boolean podeSerEditado() {
        return ativo;
    }

    public boolean podeSerRemovido() {
        return ativo;
    }

    public boolean isPessoaFisica() {
        return documento.isCpf();
    }

    public boolean isPessoaJuridica() {
        return documento.isCnpj();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cooperado cooperado = (Cooperado) o;
        return Objects.equals(id, cooperado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cooperado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", documento=" + documento +
                ", ativo=" + ativo +
                '}';
    }
}

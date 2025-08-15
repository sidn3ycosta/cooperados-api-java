package com.cooperados.infrastructure.persistence;

import com.cooperados.domain.entity.Cooperado;
import com.cooperados.domain.repository.CooperadoRepository;
import com.cooperados.domain.valueobject.Documento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Implementação concreta do repositório JPA para Cooperado
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Repository
public interface CooperadoRepositoryImpl extends JpaRepository<Cooperado, Long>, CooperadoRepository {

    /**
     * Busca cooperado por CPF
     */
    @Override
    @Query("SELECT c FROM Cooperado c WHERE c.documento.cpf.valor = :cpf AND c.ativo = true")
    Optional<Cooperado> findByCpf(@Param("cpf") String cpf);

    /**
     * Busca cooperado por CNPJ
     */
    @Override
    @Query("SELECT c FROM Cooperado c WHERE c.documento.cnpj.valor = :cnpj AND c.ativo = true")
    Optional<Cooperado> findByCnpj(@Param("cnpj") String cnpj);

    /**
     * Busca cooperado por documento (CPF ou CNPJ)
     */
    @Override
    @Query("SELECT c FROM Cooperado c WHERE " +
           "(c.documento.cpf.valor = :documento OR c.documento.cnpj.valor = :documento) " +
           "AND c.ativo = true")
    Optional<Cooperado> findByDocumento(@Param("documento") String documento);

    /**
     * Verifica se existe CPF cadastrado
     */
    @Override
    @Query("SELECT COUNT(c) > 0 FROM Cooperado c WHERE c.documento.cpf.valor = :cpf AND c.ativo = true")
    boolean existsByCpf(@Param("cpf") String cpf);

    /**
     * Verifica se existe CNPJ cadastrado
     */
    @Override
    @Query("SELECT COUNT(c) > 0 FROM Cooperado c WHERE c.documento.cnpj.valor = :cnpj AND c.ativo = true")
    boolean existsByCnpj(@Param("cnpj") String cnpj);

    /**
     * Verifica se existe documento cadastrado (CPF ou CNPJ)
     */
    @Override
    @Query("SELECT COUNT(c) > 0 FROM Cooperado c WHERE " +
           "(c.documento.cpf.valor = :documento OR c.documento.cnpj.valor = :documento) " +
           "AND c.ativo = true")
    boolean existsByDocumento(@Param("documento") String documento);

    /**
     * Busca cooperados ativos
     */
    @Override
    List<Cooperado> findByAtivoTrue();

    /**
     * Busca cooperados ativos com paginação
     */
    @Override
    Page<Cooperado> findByAtivoTrue(Pageable pageable);

    /**
     * Busca cooperados por nome (case insensitive)
     */
    @Override
    @Query("SELECT c FROM Cooperado c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%')) AND c.ativo = true")
    List<Cooperado> findByNomeContainingIgnoreCase(@Param("nome") String nome);

    /**
     * Busca cooperados por data de nascimento/constituição
     */
    @Override
    List<Cooperado> findByDataNascimentoConstituicaoBetweenAndAtivoTrue(LocalDate dataInicio, LocalDate dataFim);

    /**
     * Busca cooperados por faixa de renda/faturamento
     */
    @Override
    @Query("SELECT c FROM Cooperado c WHERE c.rendaFaturamento BETWEEN :rendaMin AND :rendaMax AND c.ativo = true")
    List<Cooperado> findByRendaFaturamentoBetween(@Param("rendaMin") java.math.BigDecimal rendaMin, 
                                                  @Param("rendaMax") java.math.BigDecimal rendaMax);

    /**
     * Busca cooperados por DDD do telefone
     */
    @Override
    @Query("SELECT c FROM Cooperado c WHERE c.telefone.valor LIKE :ddd + '%' AND c.ativo = true")
    List<Cooperado> findByTelefoneDdd(@Param("ddd") String ddd);

    /**
     * Busca cooperados com email
     */
    @Override
    @Query("SELECT c FROM Cooperado c WHERE c.email.valor IS NOT NULL AND c.ativo = true")
    List<Cooperado> findWithEmail();

    /**
     * Busca cooperados sem email
     */
    @Override
    @Query("SELECT c FROM Cooperado c WHERE c.email.valor IS NULL AND c.ativo = true")
    List<Cooperado> findWithoutEmail();

    /**
     * Conta cooperados ativos
     */
    @Override
    long countByAtivoTrue();

    /**
     * Conta cooperados por tipo de documento
     */
    @Override
    @Query("SELECT COUNT(c) FROM Cooperado c WHERE c.documento.tipo = :tipo AND c.ativo = true")
    long countByTipoDocumento(@Param("tipo") Documento.TipoDocumento tipo);

    /**
     * Busca cooperados por tipo de documento
     */
    @Override
    @Query("SELECT c FROM Cooperado c WHERE c.documento.tipo = :tipo AND c.ativo = true")
    List<Cooperado> findByTipoDocumento(@Param("tipo") Documento.TipoDocumento tipo);
}

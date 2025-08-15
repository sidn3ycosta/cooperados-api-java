package com.cooperados.application.service;

import com.cooperados.application.dto.*;
import com.cooperados.domain.entity.Cooperado;
import com.cooperados.domain.repository.CooperadoRepository;
import com.cooperados.domain.service.CooperadoDomainService;
import com.cooperados.domain.valueobject.Documento;
import com.cooperados.domain.valueobject.Email;
import com.cooperados.domain.valueobject.Telefone;
import com.cooperados.infrastructure.exception.CooperadoNaoEncontradoException;
import com.cooperados.infrastructure.exception.DocumentoJaExisteException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Serviço de aplicação para orquestrar operações de cooperado
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class CooperadoApplicationService {

    private final CooperadoRepository cooperadoRepository;
    private final CooperadoDomainService cooperadoDomainService;
    private final CooperadoMapper cooperadoMapper;

    public CooperadoApplicationService(CooperadoRepository cooperadoRepository,
                                    CooperadoDomainService cooperadoDomainService,
                                    CooperadoMapper cooperadoMapper) {
        this.cooperadoRepository = cooperadoRepository;
        this.cooperadoDomainService = cooperadoDomainService;
        this.cooperadoMapper = cooperadoMapper;
    }

    /**
     * Cria um novo cooperado
     */
    @Transactional
    public CooperadoResponse criarCooperado(CriarCooperadoRequest request) {
        // Verifica se já existe cooperado com o documento
        if (cooperadoRepository.existsByDocumento(request.getDocumento())) {
            throw new DocumentoJaExisteException("Já existe cooperado com o documento: " + request.getDocumento());
        }

        // Cria a entidade cooperado
        Cooperado cooperado = cooperadoDomainService.criarCooperado(
                request.getNome(),
                request.getDocumento(),
                request.getDataNascimentoConstituicao(),
                request.getRendaFaturamento(),
                request.getTelefone(),
                request.getEmail()
        );

        // Salva no repositório
        cooperado = cooperadoRepository.save(cooperado);

        // Retorna o DTO de resposta
        return cooperadoMapper.toResponse(cooperado);
    }

    /**
     * Busca cooperado por ID
     */
    public CooperadoResponse buscarPorId(Long id) {
        Cooperado cooperado = cooperadoRepository.findById(id)
                .orElseThrow(() -> new CooperadoNaoEncontradoException("Cooperado não encontrado com ID: " + id));

        return cooperadoMapper.toResponse(cooperado);
    }

    /**
     * Busca cooperado por documento (CPF/CNPJ)
     */
    public CooperadoResponse buscarPorDocumento(String documento) {
        Cooperado cooperado = cooperadoRepository.findByDocumento(documento)
                .orElseThrow(() -> new CooperadoNaoEncontradoException("Cooperado não encontrado com documento: " + documento));

        return cooperadoMapper.toResponse(cooperado);
    }

    /**
     * Lista todos os cooperados com paginação
     */
    public PaginatedResponse<CooperadoListResponse> listarCooperados(Pageable pageable) {
        Page<Cooperado> cooperadoPage = cooperadoRepository.findAll(pageable);
        return cooperadoMapper.toPaginatedResponse(cooperadoPage);
    }

    /**
     * Lista cooperados por tipo de documento
     */
    public List<CooperadoListResponse> listarPorTipoDocumento(Documento.TipoDocumento tipo) {
        List<Cooperado> cooperados = cooperadoRepository.findByTipoDocumento(tipo);
        return cooperadoMapper.toListResponseList(cooperados);
    }

    /**
     * Lista cooperados ativos
     */
    public List<CooperadoListResponse> listarCooperadosAtivos() {
        List<Cooperado> cooperados = cooperadoRepository.findByAtivoTrue();
        return cooperadoMapper.toListResponseList(cooperados);
    }

    /**
     * Atualiza um cooperado existente
     */
    @Transactional
    public CooperadoResponse atualizarCooperado(Long id, AtualizarCooperadoRequest request) {
        // Busca o cooperado existente
        Cooperado cooperado = cooperadoRepository.findById(id)
                .orElseThrow(() -> new CooperadoNaoEncontradoException("Cooperado não encontrado com ID: " + id));

        // Atualiza apenas os campos fornecidos
        if (request.hasNome()) {
            cooperado.setNome(request.getNome());
        }

        if (request.hasDataNascimentoConstituicao()) {
            cooperado.setDataNascimentoConstituicao(request.getDataNascimentoConstituicao());
        }

        if (request.hasRendaFaturamento()) {
            cooperado.setRendaFaturamento(request.getRendaFaturamento());
        }

        if (request.hasTelefone()) {
            cooperado.setTelefone(new Telefone(request.getTelefone()));
        }

        if (request.hasEmail()) {
            cooperado.setEmail(new Email(request.getEmail()));
        }

        // Salva as alterações
        cooperado = cooperadoRepository.save(cooperado);

        // Retorna o DTO de resposta
        return cooperadoMapper.toResponse(cooperado);
    }

    /**
     * Remove um cooperado (exclusão lógica)
     */
    @Transactional
    public void removerCooperado(Long id) {
        Cooperado cooperado = cooperadoRepository.findById(id)
                .orElseThrow(() -> new CooperadoNaoEncontradoException("Cooperado não encontrado com ID: " + id));

        cooperado.remover();
        cooperadoRepository.save(cooperado);
    }

    /**
     * Reativa um cooperado removido
     */
    @Transactional
    public CooperadoResponse reativarCooperado(Long id) {
        Cooperado cooperado = cooperadoRepository.findById(id)
                .orElseThrow(() -> new CooperadoNaoEncontradoException("Cooperado não encontrado com ID: " + id));

        cooperado.reativar();
        cooperado = cooperadoRepository.save(cooperado);

        return cooperadoMapper.toResponse(cooperado);
    }

    /**
     * Verifica se existe cooperado com o documento
     */
    public boolean existePorDocumento(String documento) {
        return cooperadoRepository.existsByDocumento(documento);
    }

    /**
     * Conta cooperados por tipo de documento
     */
    public long contarPorTipoDocumento(Documento.TipoDocumento tipo) {
        return cooperadoRepository.countByTipoDocumento(tipo);
    }
}

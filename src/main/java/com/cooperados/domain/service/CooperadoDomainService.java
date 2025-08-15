package com.cooperados.domain.service;

import com.cooperados.domain.entity.Cooperado;
import com.cooperados.domain.repository.CooperadoRepository;
import com.cooperados.domain.valueobject.Documento;
import com.cooperados.domain.valueobject.Email;
import com.cooperados.domain.valueobject.Telefone;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Serviço de domínio para regras de negócio da entidade Cooperado
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class CooperadoDomainService {

    private final CooperadoRepository cooperadoRepository;

    public CooperadoDomainService(CooperadoRepository cooperadoRepository) {
        this.cooperadoRepository = cooperadoRepository;
    }

    /**
     * Valida se um documento (CPF/CNPJ) já está cadastrado
     */
    public boolean documentoJaCadastrado(String documento) {
        return cooperadoRepository.existsByDocumento(documento);
    }

    /**
     * Valida se um CPF já está cadastrado
     */
    public boolean cpfJaCadastrado(String cpf) {
        return cooperadoRepository.existsByCpf(cpf);
    }

    /**
     * Valida se um CNPJ já está cadastrado
     */
    public boolean cnpjJaCadastrado(String cnpj) {
        return cooperadoRepository.existsByCnpj(cnpj);
    }

    /**
     * Cria um novo cooperado com validações de domínio
     */
    @Transactional
    public Cooperado criarCooperado(String nome, String documento, LocalDate dataNascimentoConstituicao,
                                   BigDecimal rendaFaturamento, String telefone, String email) {
        
        // Validações de domínio
        validarNome(nome);
        validarDataNascimentoConstituicao(dataNascimentoConstituicao);
        validarRendaFaturamento(rendaFaturamento);
        
        // Verifica se documento já está cadastrado
        if (documentoJaCadastrado(documento)) {
            throw new IllegalStateException("Documento já está cadastrado no sistema");
        }

        // Cria os value objects
        Documento documentoObj = new Documento(documento);
        Telefone telefoneObj = new Telefone(telefone);
        Email emailObj = email != null ? new Email(email) : null;

        // Cria e retorna o cooperado
        return new Cooperado(nome, documentoObj, dataNascimentoConstituicao, 
                           rendaFaturamento, telefoneObj, emailObj);
    }

    /**
     * Atualiza um cooperado existente
     */
    @Transactional
    public void atualizarCooperado(Cooperado cooperado, String novoNome, BigDecimal novaRenda,
                                  String novoTelefone, String novoEmail) {
        
        if (!cooperado.podeSerEditado()) {
            throw new IllegalStateException("Cooperado não pode ser editado");
        }

        if (novoNome != null) {
            validarNome(novoNome);
            cooperado.atualizarNome(novoNome);
        }

        if (novaRenda != null) {
            validarRendaFaturamento(novaRenda);
            cooperado.atualizarRendaFaturamento(novaRenda);
        }

        if (novoTelefone != null) {
            Telefone telefoneObj = new Telefone(novoTelefone);
            cooperado.atualizarTelefone(telefoneObj);
        }

        if (novoEmail != null) {
            Email emailObj = new Email(novoEmail);
            cooperado.atualizarEmail(emailObj);
        }
    }

    /**
     * Remove um cooperado (exclusão lógica)
     */
    @Transactional
    public void removerCooperado(Cooperado cooperado) {
        if (!cooperado.podeSerRemovido()) {
            throw new IllegalStateException("Cooperado não pode ser removido");
        }
        cooperado.desativar();
    }

    /**
     * Reativa um cooperado
     */
    @Transactional
    public void reativarCooperado(Cooperado cooperado) {
        cooperado.ativar();
    }

    /**
     * Validações de domínio
     */
    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (nome.trim().length() < 2) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 2 caracteres");
        }
        if (nome.trim().length() > 255) {
            throw new IllegalArgumentException("Nome deve ter no máximo 255 caracteres");
        }
    }

    private void validarDataNascimentoConstituicao(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Data de nascimento/constituição é obrigatória");
        }
        
        LocalDate hoje = LocalDate.now();
        if (data.isAfter(hoje)) {
            throw new IllegalArgumentException("Data de nascimento/constituição não pode ser futura");
        }
        
        // Para pessoa física, data não pode ser muito antiga (mais de 150 anos)
        if (data.isBefore(hoje.minusYears(150))) {
            throw new IllegalArgumentException("Data de nascimento/constituição muito antiga");
        }
    }

    private void validarRendaFaturamento(BigDecimal renda) {
        if (renda == null) {
            throw new IllegalArgumentException("Renda/faturamento é obrigatório");
        }
        if (renda.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Renda/faturamento deve ser positivo");
        }
        if (renda.compareTo(new BigDecimal("999999999999.99")) > 0) {
            throw new IllegalArgumentException("Renda/faturamento muito alto");
        }
    }

    /**
     * Busca cooperados por critérios específicos
     */
    public List<Cooperado> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome para busca é obrigatório");
        }
        return cooperadoRepository.findByNomeContainingIgnoreCase(nome.trim());
    }

    public List<Cooperado> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio == null || dataFim == null) {
            throw new IllegalArgumentException("Data início e fim são obrigatórias");
        }
        if (dataInicio.isAfter(dataFim)) {
            throw new IllegalArgumentException("Data início deve ser anterior à data fim");
        }
        return cooperadoRepository.findByDataNascimentoConstituicaoBetweenAndAtivoTrue(dataInicio, dataFim);
    }

    public List<Cooperado> buscarPorFaixaRenda(BigDecimal rendaMin, BigDecimal rendaMax) {
        if (rendaMin == null || rendaMax == null) {
            throw new IllegalArgumentException("Renda mínima e máxima são obrigatórias");
        }
        if (rendaMin.compareTo(rendaMax) > 0) {
            throw new IllegalArgumentException("Renda mínima deve ser menor que a máxima");
        }
        return cooperadoRepository.findByRendaFaturamentoBetween(rendaMin, rendaMax);
    }

    public List<Cooperado> buscarPorDdd(String ddd) {
        if (ddd == null || ddd.trim().isEmpty()) {
            throw new IllegalArgumentException("DDD é obrigatório");
        }
        if (ddd.length() != 2) {
            throw new IllegalArgumentException("DDD deve ter 2 dígitos");
        }
        return cooperadoRepository.findByTelefoneDdd(ddd.trim());
    }
}

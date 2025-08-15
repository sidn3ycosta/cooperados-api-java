package com.cooperados.application.controller;

import com.cooperados.application.dto.*;
import com.cooperados.application.service.CooperadoApplicationService;
import com.cooperados.domain.valueobject.Documento;
import com.cooperados.infrastructure.exception.CooperadoNaoEncontradoException;
import com.cooperados.infrastructure.exception.DocumentoJaExisteException;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para operações de cooperado
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/cooperados")
@CrossOrigin(origins = "*")
public class CooperadoController {

    private final CooperadoApplicationService cooperadoApplicationService;

    public CooperadoController(CooperadoApplicationService cooperadoApplicationService) {
        this.cooperadoApplicationService = cooperadoApplicationService;
    }

    /**
     * POST /api/v1/cooperados
     * Cria um novo cooperado
     */
    @PostMapping
    public ResponseEntity<CooperadoResponse> criarCooperado(@Valid @RequestBody CriarCooperadoRequest request) {
        try {
            CooperadoResponse response = cooperadoApplicationService.criarCooperado(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DocumentoJaExisteException e) {
            throw e; // Será tratado pelo GlobalExceptionHandler
        }
    }

    /**
     * GET /api/v1/cooperados/{id}
     * Busca cooperado por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<CooperadoResponse> buscarPorId(@PathVariable Long id) {
        try {
            CooperadoResponse response = cooperadoApplicationService.buscarPorId(id);
            return ResponseEntity.ok(response);
        } catch (CooperadoNaoEncontradoException e) {
            throw e; // Será tratado pelo GlobalExceptionHandler
        }
    }

    /**
     * GET /api/v1/cooperados/documento/{documento}
     * Busca cooperado por documento (CPF/CNPJ)
     */
    @GetMapping("/documento/{documento}")
    public ResponseEntity<CooperadoResponse> buscarPorDocumento(@PathVariable String documento) {
        try {
            CooperadoResponse response = cooperadoApplicationService.buscarPorDocumento(documento);
            return ResponseEntity.ok(response);
        } catch (CooperadoNaoEncontradoException e) {
            throw e; // Será tratado pelo GlobalExceptionHandler
        }
    }

    /**
     * GET /api/v1/cooperados
     * Lista todos os cooperados com paginação
     */
    @GetMapping
    public ResponseEntity<PaginatedResponse<CooperadoListResponse>> listarCooperados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = Sort.Direction.fromString(direction.toUpperCase());
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));

        PaginatedResponse<CooperadoListResponse> response = cooperadoApplicationService.listarCooperados(pageable);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/v1/cooperados/tipo/{tipo}
     * Lista cooperados por tipo de documento
     */
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CooperadoListResponse>> listarPorTipoDocumento(
            @PathVariable String tipo) {
        try {
            Documento.TipoDocumento tipoDocumento = Documento.TipoDocumento.valueOf(tipo.toUpperCase());
            List<CooperadoListResponse> response = cooperadoApplicationService.listarPorTipoDocumento(tipoDocumento);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /api/v1/cooperados/ativos
     * Lista apenas cooperados ativos
     */
    @GetMapping("/ativos")
    public ResponseEntity<List<CooperadoListResponse>> listarCooperadosAtivos() {
        List<CooperadoListResponse> response = cooperadoApplicationService.listarCooperadosAtivos();
        return ResponseEntity.ok(response);
    }

    /**
     * PUT /api/v1/cooperados/{id}
     * Atualiza um cooperado existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<CooperadoResponse> atualizarCooperado(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarCooperadoRequest request) {
        try {
            CooperadoResponse response = cooperadoApplicationService.atualizarCooperado(id, request);
            return ResponseEntity.ok(response);
        } catch (CooperadoNaoEncontradoException e) {
            throw e; // Será tratado pelo GlobalExceptionHandler
        }
    }

    /**
     * DELETE /api/v1/cooperados/{id}
     * Remove um cooperado (exclusão lógica)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCooperado(@PathVariable Long id) {
        try {
            cooperadoApplicationService.removerCooperado(id);
            return ResponseEntity.noContent().build();
        } catch (CooperadoNaoEncontradoException e) {
            throw e; // Será tratado pelo GlobalExceptionHandler
        }
    }

    /**
     * PATCH /api/v1/cooperados/{id}/reativar
     * Reativa um cooperado removido
     */
    @PatchMapping("/{id}/reativar")
    public ResponseEntity<CooperadoResponse> reativarCooperado(@PathVariable Long id) {
        try {
            CooperadoResponse response = cooperadoApplicationService.reativarCooperado(id);
            return ResponseEntity.ok(response);
        } catch (CooperadoNaoEncontradoException e) {
            throw e; // Será tratado pelo GlobalExceptionHandler
        }
    }

    /**
     * GET /api/v1/cooperados/existe/{documento}
     * Verifica se existe cooperado com o documento
     */
    @GetMapping("/existe/{documento}")
    public ResponseEntity<Boolean> existePorDocumento(@PathVariable String documento) {
        boolean existe = cooperadoApplicationService.existePorDocumento(documento);
        return ResponseEntity.ok(existe);
    }

    /**
     * GET /api/v1/cooperados/contar/tipo/{tipo}
     * Conta cooperados por tipo de documento
     */
    @GetMapping("/contar/tipo/{tipo}")
    public ResponseEntity<Long> contarPorTipoDocumento(@PathVariable String tipo) {
        try {
            Documento.TipoDocumento tipoDocumento = Documento.TipoDocumento.valueOf(tipo.toUpperCase());
            long count = cooperadoApplicationService.contarPorTipoDocumento(tipoDocumento);
            return ResponseEntity.ok(count);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /api/v1/cooperados/health
     * Endpoint de saúde da API
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Cooperados API está funcionando!");
    }
}

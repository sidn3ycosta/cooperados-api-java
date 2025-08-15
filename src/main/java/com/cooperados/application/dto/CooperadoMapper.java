package com.cooperados.application.dto;

import com.cooperados.domain.entity.Cooperado;
import com.cooperados.domain.valueobject.Documento;
import com.cooperados.domain.valueobject.Email;
import com.cooperados.domain.valueobject.Telefone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Mapper para conversão entre entidades e DTOs
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Mapper(componentModel = "spring")
public interface CooperadoMapper {

    /**
     * Converte entidade para DTO de resposta completo
     */
    @Mapping(source = "documento.tipo", target = "tipoDocumento")
    @Mapping(source = "documento", target = "documento", qualifiedByName = "documentoToValor")
    @Mapping(source = "documento", target = "documentoFormatado", qualifiedByName = "documentoToFormatado")
    @Mapping(source = "telefone", target = "telefone", qualifiedByName = "telefoneToValor")
    @Mapping(source = "telefone", target = "telefoneFormatado", qualifiedByName = "telefoneToFormatado")
    @Mapping(source = "email", target = "email", qualifiedByName = "emailToValor")
    CooperadoResponse toResponse(Cooperado cooperado);

    /**
     * Converte entidade para DTO de listagem
     */
    @Mapping(source = "documento.tipo", target = "tipoDocumento")
    @Mapping(source = "documento", target = "documentoFormatado", qualifiedByName = "documentoToFormatado")
    @Mapping(source = "telefone", target = "telefoneFormatado", qualifiedByName = "telefoneToFormatado")
    @Mapping(source = "email", target = "email", qualifiedByName = "emailToValor")
    CooperadoListResponse toListResponse(Cooperado cooperado);

    /**
     * Converte lista de entidades para lista de DTOs de listagem
     */
    List<CooperadoListResponse> toListResponseList(List<Cooperado> cooperados);

    /**
     * Converte página de entidades para página de DTOs de listagem
     */
    @Mapping(source = "content", target = "content")
    @Mapping(source = "number", target = "pageNumber")
    @Mapping(source = "size", target = "pageSize")
    @Mapping(source = "totalElements", target = "totalElements")
    @Mapping(source = "totalPages", target = "totalPages")
    @Mapping(source = "first", target = "first")
    @Mapping(source = "last", target = "last")
    @Mapping(target = "hasNext", expression = "java(!cooperadoPage.isLast())")
    @Mapping(target = "hasPrevious", expression = "java(!cooperadoPage.isFirst())")
    PaginatedResponse<CooperadoListResponse> toPaginatedResponse(Page<Cooperado> cooperadoPage);

    // Métodos auxiliares para mapeamento de Value Objects

    @Named("documentoToValor")
    default String documentoToValor(Documento documento) {
        if (documento == null) return null;
        return documento.getValor();
    }

    @Named("documentoToFormatado")
    default String documentoToFormatado(Documento documento) {
        if (documento == null) return null;
        return documento.getValorFormatado();
    }

    @Named("telefoneToValor")
    default String telefoneToValor(Telefone telefone) {
        if (telefone == null) return null;
        return telefone.getValor();
    }

    @Named("telefoneToFormatado")
    default String telefoneToFormatado(Telefone telefone) {
        if (telefone == null) return null;
        return telefone.getValorFormatado();
    }

    @Named("emailToValor")
    default String emailToValor(Email email) {
        if (email == null) return null;
        return email.getValor();
    }
}

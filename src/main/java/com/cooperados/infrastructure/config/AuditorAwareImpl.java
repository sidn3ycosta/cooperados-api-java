package com.cooperados.infrastructure.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementação de AuditorAware para fornecer informações do usuário atual
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    /**
     * Retorna o identificador do usuário atual
     * Por enquanto retorna "SYSTEM", mas pode ser integrado com Spring Security
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        // TODO: Integrar com Spring Security para obter usuário autenticado
        // Por enquanto retorna um valor padrão
        return Optional.of("SYSTEM");
    }
}

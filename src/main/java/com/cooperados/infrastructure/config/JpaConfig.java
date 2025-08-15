package com.cooperados.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuração JPA para a aplicação
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.cooperados.infrastructure.persistence")
@EnableTransactionManagement
public class JpaConfig {
    
    // Configuração padrão do Spring Data JPA
    // As anotações acima habilitam:
    // - @EnableJpaAuditing: Auditoria automática (@CreatedDate, @LastModifiedDate)
    // - @EnableJpaRepositories: Repositórios JPA
    // - @EnableTransactionManagement: Gerenciamento de transações
}

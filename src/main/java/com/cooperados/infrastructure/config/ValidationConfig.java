package com.cooperados.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * Configuração de validação para a aplicação
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Configuration
public class ValidationConfig {

    /**
     * Configura o validador local para validação de beans
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Habilita validação de parâmetros de método
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}

package com.cooperados.shared.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Anotação de validação para documento (CPF ou CNPJ)
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Documented
@Constraint(validatedBy = DocumentoValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDocumento {
    
    String message() default "Documento deve ser um CPF ou CNPJ válido";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}

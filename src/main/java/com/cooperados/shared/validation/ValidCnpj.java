package com.cooperados.shared.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Anotação de validação para CNPJ
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Documented
@Constraint(validatedBy = CnpjValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCnpj {
    
    String message() default "CNPJ inválido";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}

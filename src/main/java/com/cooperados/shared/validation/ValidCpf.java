package com.cooperados.shared.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Anotação de validação para CPF
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Documented
@Constraint(validatedBy = CpfValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCpf {
    
    String message() default "CPF inválido";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}

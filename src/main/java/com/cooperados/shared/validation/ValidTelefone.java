package com.cooperados.shared.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Anotação de validação para telefone brasileiro
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Documented
@Constraint(validatedBy = TelefoneValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTelefone {
    
    String message() default "Telefone inválido";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}

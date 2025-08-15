package com.cooperados.shared.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Anotação de validação para e-mail
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
    
    String message() default "E-mail inválido";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}

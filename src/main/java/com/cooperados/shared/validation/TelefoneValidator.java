package com.cooperados.shared.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Validador customizado para telefone brasileiro
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
public class TelefoneValidator implements ConstraintValidator<ValidTelefone, String> {

    // Padrões para telefones brasileiros
    private static final Pattern TELEFONE_PATTERN = Pattern.compile(
        "^(\\(?[1-9]{2}\\)?)?[0-9]{4,5}[0-9]{4}$"
    );
    
    private static final Pattern CELULAR_PATTERN = Pattern.compile(
        "^(\\(?[1-9]{2}\\)?)?[0-9]{5}[0-9]{4}$"
    );
    
    private static final Pattern FIXO_PATTERN = Pattern.compile(
        "^(\\(?[1-9]{2}\\)?)?[0-9]{4}[0-9]{4}$"
    );

    @Override
    public void initialize(ValidTelefone constraintAnnotation) {
        // Inicialização não necessária
    }

    @Override
    public boolean isValid(String telefone, ConstraintValidatorContext context) {
        if (telefone == null || telefone.trim().isEmpty()) {
            return false;
        }

        // Remove caracteres não numéricos
        String telefoneLimpo = telefone.replaceAll("[^0-9]", "");
        
        // Verifica se tem entre 10 e 11 dígitos
        if (telefoneLimpo.length() < 10 || telefoneLimpo.length() > 11) {
            return false;
        }

        // Verifica se o DDD é válido (11-99)
        String ddd = telefoneLimpo.length() == 11 ? telefoneLimpo.substring(0, 2) : telefoneLimpo.substring(0, 2);
        int dddNumero = Integer.parseInt(ddd);
        if (dddNumero < 11 || dddNumero > 99) {
            return false;
        }

        // Verifica se é um telefone fixo ou celular válido
        return TELEFONE_PATTERN.matcher(telefone).matches() ||
               CELULAR_PATTERN.matcher(telefone).matches() ||
               FIXO_PATTERN.matcher(telefone).matches();
    }

    /**
     * Verifica se é um celular
     */
    public static boolean isCelular(String telefone) {
        if (telefone == null) return false;
        String telefoneLimpo = telefone.replaceAll("[^0-9]", "");
        return telefoneLimpo.length() == 11 && CELULAR_PATTERN.matcher(telefone).matches();
    }

    /**
     * Verifica se é um telefone fixo
     */
    public static boolean isFixo(String telefone) {
        if (telefone == null) return false;
        String telefoneLimpo = telefone.replaceAll("[^0-9]", "");
        return telefoneLimpo.length() == 10 && FIXO_PATTERN.matcher(telefone).matches();
    }

    /**
     * Formata o telefone para exibição
     */
    public static String formatar(String telefone) {
        if (telefone == null) return null;
        
        String telefoneLimpo = telefone.replaceAll("[^0-9]", "");
        
        if (telefoneLimpo.length() == 11) {
            // Celular: (11) 98765-4321
            return String.format("(%s) %s-%s", 
                telefoneLimpo.substring(0, 2),
                telefoneLimpo.substring(2, 7),
                telefoneLimpo.substring(7));
        } else if (telefoneLimpo.length() == 10) {
            // Fixo: (11) 3456-7890
            return String.format("(%s) %s-%s", 
                telefoneLimpo.substring(0, 2),
                telefoneLimpo.substring(2, 6),
                telefoneLimpo.substring(6));
        }
        
        return telefone;
    }
}

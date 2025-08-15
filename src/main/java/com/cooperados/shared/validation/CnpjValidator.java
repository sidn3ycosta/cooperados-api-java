package com.cooperados.shared.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validador customizado para CNPJ com algoritmo oficial da Receita Federal
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
public class CnpjValidator implements ConstraintValidator<ValidCnpj, String> {

    @Override
    public void initialize(ValidCnpj constraintAnnotation) {
        // Inicialização não necessária
    }

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            return false;
        }

        // Remove caracteres não numéricos
        String cnpjLimpo = cnpj.replaceAll("[^0-9]", "");
        
        // Verifica se tem 14 dígitos
        if (cnpjLimpo.length() != 14) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (CNPJ inválido)
        if (cnpjLimpo.matches("(\\d)\\1{13}")) {
            return false;
        }

        // Validação do primeiro dígito verificador
        int soma = 0;
        int peso = 2;
        
        for (int i = 11; i >= 0; i--) {
            soma += Character.getNumericValue(cnpjLimpo.charAt(i)) * peso;
            peso = peso == 9 ? 2 : peso + 1;
        }
        
        int resto = soma % 11;
        int primeiroDigito = resto < 2 ? 0 : 11 - resto;
        
        if (Character.getNumericValue(cnpjLimpo.charAt(12)) != primeiroDigito) {
            return false;
        }

        // Validação do segundo dígito verificador
        soma = 0;
        peso = 2;
        
        for (int i = 12; i >= 0; i--) {
            soma += Character.getNumericValue(cnpjLimpo.charAt(i)) * peso;
            peso = peso == 9 ? 2 : peso + 1;
        }
        
        resto = soma % 11;
        int segundoDigito = resto < 2 ? 0 : 11 - resto;
        
        return Character.getNumericValue(cnpjLimpo.charAt(13)) == segundoDigito;
    }
}

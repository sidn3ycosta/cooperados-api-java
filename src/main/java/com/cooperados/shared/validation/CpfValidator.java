package com.cooperados.shared.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Pattern;

/**
 * Validador customizado para CPF com algoritmo oficial da Receita Federal
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
public class CpfValidator implements ConstraintValidator<ValidCpf, String> {

    @Override
    public void initialize(ValidCpf constraintAnnotation) {
        // Inicialização não necessária
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }

        // Remove caracteres não numéricos
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        
        // Verifica se tem 11 dígitos
        if (cpfLimpo.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (CPF inválido)
        if (cpfLimpo.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Validação do primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpfLimpo.charAt(i)) * (10 - i);
        }
        
        int resto = soma % 11;
        int primeiroDigito = resto < 2 ? 0 : 11 - resto;
        
        if (Character.getNumericValue(cpfLimpo.charAt(9)) != primeiroDigito) {
            return false;
        }

        // Validação do segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpfLimpo.charAt(i)) * (11 - i);
        }
        
        resto = soma % 11;
        int segundoDigito = resto < 2 ? 0 : 11 - resto;
        
        return Character.getNumericValue(cpfLimpo.charAt(10)) == segundoDigito;
    }
}

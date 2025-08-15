package com.cooperados.shared.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validador para documento (CPF ou CNPJ)
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
public class DocumentoValidator implements ConstraintValidator<ValidDocumento, String> {

    @Override
    public void initialize(ValidDocumento constraintAnnotation) {
        // Inicialização não necessária
    }

    @Override
    public boolean isValid(String documento, ConstraintValidatorContext context) {
        if (documento == null || documento.trim().isEmpty()) {
            return false;
        }

        // Remove caracteres não numéricos
        String documentoLimpo = documento.replaceAll("[^0-9]", "");
        
        // Verifica se tem 11 dígitos (CPF) ou 14 dígitos (CNPJ)
        if (documentoLimpo.length() == 11) {
            CpfValidator cpfValidator = new CpfValidator();
            return cpfValidator.isValid(documentoLimpo, context);
        } else if (documentoLimpo.length() == 14) {
            CnpjValidator cnpjValidator = new CnpjValidator();
            return cnpjValidator.isValid(documentoLimpo, context);
        }
        
        return false;
    }

    /**
     * Verifica se é um CPF
     */
    public static boolean isCpf(String documento) {
        if (documento == null) return false;
        String documentoLimpo = documento.replaceAll("[^0-9]", "");
        return documentoLimpo.length() == 11;
    }

    /**
     * Verifica se é um CNPJ
     */
    public static boolean isCnpj(String documento) {
        if (documento == null) return false;
        String documentoLimpo = documento.replaceAll("[^0-9]", "");
        return documentoLimpo.length() == 14;
    }

    /**
     * Retorna o tipo do documento
     */
    public static String getTipo(String documento) {
        if (isCpf(documento)) {
            return "CPF";
        } else if (isCnpj(documento)) {
            return "CNPJ";
        }
        return "INVALIDO";
    }
}

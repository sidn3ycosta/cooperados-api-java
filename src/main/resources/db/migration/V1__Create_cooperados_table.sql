-- Migration: V1__Create_cooperados_table.sql
-- Description: Cria a tabela principal de cooperados com todos os campos necessários
-- Author: Cooperados Team
-- Version: 1.0.0

CREATE TABLE cooperados (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    
    -- Campos para CPF
    documento_cpf VARCHAR(11),
    documento_cnpj VARCHAR(14),
    tipo_documento VARCHAR(4) NOT NULL CHECK (tipo_documento IN ('CPF', 'CNPJ')),
    
    -- Campos obrigatórios
    data_nascimento_constituicao DATE NOT NULL,
    renda_faturamento DECIMAL(15,2) NOT NULL CHECK (renda_faturamento > 0),
    telefone VARCHAR(11) NOT NULL,
    
    -- Campo opcional
    email VARCHAR(255),
    
    -- Campos de controle
    ativo BOOLEAN NOT NULL DEFAULT true,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    versao BIGINT NOT NULL DEFAULT 1,
    
    -- Constraints de unicidade
    CONSTRAINT uk_cooperados_cpf UNIQUE (documento_cpf),
    CONSTRAINT uk_cooperados_cnpj UNIQUE (documento_cnpj),
    
    -- Constraints de validação
    CONSTRAINT chk_cooperados_documento_tipo CHECK (
        (tipo_documento = 'CPF' AND documento_cpf IS NOT NULL AND documento_cnpj IS NULL) OR
        (tipo_documento = 'CNPJ' AND documento_cnpj IS NOT NULL AND documento_cpf IS NULL)
    ),
    
    CONSTRAINT chk_cooperados_data_futura CHECK (data_nascimento_constituicao <= CURRENT_DATE),
    CONSTRAINT chk_cooperados_telefone CHECK (telefone ~ '^[0-9]{10,11}$')
);

-- Índices para performance
CREATE INDEX idx_cooperados_nome ON cooperados(nome);
CREATE INDEX idx_cooperados_tipo_documento ON cooperados(tipo_documento);
CREATE INDEX idx_cooperados_ativo ON cooperados(ativo);
CREATE INDEX idx_cooperados_data_nascimento ON cooperados(data_nascimento_constituicao);
CREATE INDEX idx_cooperados_renda_faturamento ON cooperados(renda_faturamento);
CREATE INDEX idx_cooperados_telefone ON cooperados(telefone);
CREATE INDEX idx_cooperados_email ON cooperados(email) WHERE email IS NOT NULL;

-- Comentários da tabela
COMMENT ON TABLE cooperados IS 'Tabela principal para armazenar dados dos cooperados';
COMMENT ON COLUMN cooperados.id IS 'Identificador único do cooperado';
COMMENT ON COLUMN cooperados.nome IS 'Nome completo do cooperado';
COMMENT ON COLUMN cooperados.documento_cpf IS 'CPF do cooperado (pessoa física)';
COMMENT ON COLUMN cooperados.documento_cnpj IS 'CNPJ do cooperado (pessoa jurídica)';
COMMENT ON COLUMN cooperados.tipo_documento IS 'Tipo do documento: CPF ou CNPJ';
COMMENT ON COLUMN cooperados.data_nascimento_constituicao IS 'Data de nascimento (pessoa física) ou constituição (pessoa jurídica)';
COMMENT ON COLUMN cooperados.renda_faturamento IS 'Renda mensal (pessoa física) ou faturamento anual (pessoa jurídica)';
COMMENT ON COLUMN cooperados.telefone IS 'Telefone de contato';
COMMENT ON COLUMN cooperados.email IS 'E-mail de contato (opcional)';
COMMENT ON COLUMN cooperados.ativo IS 'Indica se o cooperado está ativo no sistema';
COMMENT ON COLUMN cooperados.data_criacao IS 'Data e hora de criação do registro';
COMMENT ON COLUMN cooperados.data_atualizacao IS 'Data e hora da última atualização';
COMMENT ON COLUMN cooperados.versao IS 'Versão do registro para controle de concorrência';

-- Migration: V2__Insert_sample_data.sql
-- Description: Insere dados de exemplo para testes e demonstração
-- Author: Cooperados Team
-- Version: 1.0.0

-- Inserindo cooperados pessoa física (CPF)
INSERT INTO cooperados (
    nome, 
    documento_cpf, 
    tipo_documento, 
    data_nascimento_constituicao, 
    renda_faturamento, 
    telefone, 
    email, 
    ativo, 
    data_criacao, 
    versao
) VALUES 
    ('João Silva Santos', '12345678901', 'CPF', '1985-03-15', 3500.00, '11987654321', 'joao.silva@email.com', true, CURRENT_TIMESTAMP, 1),
    ('Maria Oliveira Costa', '98765432100', 'CPF', '1990-07-22', 4200.00, '11912345678', 'maria.oliveira@email.com', true, CURRENT_TIMESTAMP, 1),
    ('Pedro Almeida Lima', '45678912300', 'CPF', '1982-11-08', 2800.00, '11955554444', 'pedro.almeida@email.com', true, CURRENT_TIMESTAMP, 1),
    ('Ana Paula Ferreira', '78912345600', 'CPF', '1988-05-30', 3800.00, '11933332222', 'ana.ferreira@email.com', true, CURRENT_TIMESTAMP, 1),
    ('Carlos Eduardo Souza', '32165498700', 'CPF', '1975-12-14', 5200.00, '11977776666', 'carlos.souza@email.com', true, CURRENT_TIMESTAMP, 1);

-- Inserindo cooperados pessoa jurídica (CNPJ)
INSERT INTO cooperados (
    nome, 
    documento_cnpj, 
    tipo_documento, 
    data_nascimento_constituicao, 
    renda_faturamento, 
    telefone, 
    email, 
    ativo, 
    data_criacao, 
    versao
) VALUES 
    ('Empresa ABC Ltda', '12345678000195', 'CNPJ', '2010-03-20', 150000.00, '11987654321', 'contato@empresaabc.com.br', true, CURRENT_TIMESTAMP, 1),
    ('Comércio XYZ S/A', '98765432000187', 'CNPJ', '2008-08-15', 280000.00, '11912345678', 'vendas@comercioxyz.com.br', true, CURRENT_TIMESTAMP, 1),
    ('Indústria 123 Ltda', '45678912000134', 'CNPJ', '2015-01-10', 450000.00, '11955554444', 'contato@industria123.com.br', true, CURRENT_TIMESTAMP, 1),
    ('Serviços DEF Ltda', '78912345000167', 'CNPJ', '2012-06-25', 120000.00, '11933332222', 'atendimento@servicosdef.com.br', true, CURRENT_TIMESTAMP, 1),
    ('Tecnologia GHI Ltda', '32165498000123', 'CNPJ', '2018-11-30', 320000.00, '11977776666', 'suporte@tecnologiaghi.com.br', true, CURRENT_TIMESTAMP, 1);

-- Comentários sobre os dados inseridos
COMMENT ON TABLE cooperados IS 'Dados de exemplo inseridos para demonstração da API';

# 🚀 Collection Postman - Cooperados API

**Collection completa para testar a API durante entrevista técnica.**

## 📥 **Como Importar**

### **1. Download da Collection**
- Baixe o arquivo `Cooperados-API.postman_collection.json`
- Ou copie o conteúdo e cole no Postman

### **2. Importar no Postman**
1. Abra o **Postman**
2. Clique em **"Import"**
3. Arraste o arquivo ou cole o conteúdo
4. Clique em **"Import"**

## 🎯 **Estrutura da Collection**

### **🏥 Health Check**
- **Health Check**: Verifica se a aplicação está funcionando

### **👥 Cooperados - CRUD**
- **Listar Todos**: Com paginação e ordenação
- **Buscar por ID**: Busca cooperado específico
- **Criar PF**: Cria pessoa física (CPF válido)
- **Criar PJ**: Cria pessoa jurídica (CNPJ válido)
- **Atualizar**: Modifica cooperado existente
- **Remover**: Soft delete (não apaga do banco)
- **Reativar**: Reativa cooperado removido

### **🔍 Consultas Específicas**
- **Buscar por CPF**: Documento específico
- **Buscar por CNPJ**: Documento específico
- **Listar por Tipo**: CPF ou CNPJ
- **Listar Ativos**: Apenas não removidos

### **📊 Informações e Estatísticas**
- **Verificar Existência**: Se documento existe
- **Contar por Tipo**: Quantidade de PF/PJ

### **🧪 Testes de Validação**
- **CPF Inválido**: Testa validação
- **CNPJ Inválido**: Testa validação
- **Email Inválido**: Testa validação
- **Telefone Inválido**: Testa validação

## 🚀 **Como Usar**

### **1. Configurar Ambiente**
- A collection usa a variável `{{base_url}}`
- **Valor padrão**: `http://localhost:8081`
- **Para produção**: Altere para a URL correta

### **2. Ordem de Testes Recomendada**
```bash
1. Health Check (verificar se API está rodando)
2. Listar Todos (ver dados existentes)
3. Criar Cooperado PF (testar criação)
4. Criar Cooperado PJ (testar criação)
5. Buscar por ID (testar busca)
6. Atualizar (testar modificação)
7. Testes de Validação (testar regras)
8. Remover e Reativar (testar soft delete)
```

### **3. Dados de Exemplo**
- **CPF Válido**: `12345678901`
- **CNPJ Válido**: `11222333000181`
- **Emails**: Formatos válidos e inválidos
- **Telefones**: Formatos válidos e inválidos

## 🧪 **Testes Automáticos**

### **✅ Testes Incluídos**
- **Status Code**: 200, 201, 400, 404
- **Response Time**: Menos de 2000ms
- **Content**: Resposta não vazia
- **Logs**: Console para debug

### **📝 Como Ver Resultados**
1. Execute uma requisição
2. Vá para a aba **"Test Results"**
3. Veja os testes que passaram/falharam
4. Consulte o **Console** para logs

## 🔧 **Troubleshooting**

### **Problema: "Could not get any response"**
- ✅ Verifique se a API está rodando
- ✅ Confirme a URL base (`{{base_url}}`)
- ✅ Teste o Health Check primeiro

### **Problema: "Connection refused"**
- ✅ Verifique se PostgreSQL está rodando
- ✅ Confirme se a aplicação iniciou
- ✅ Verifique as portas (8081, 5432)

### **Problema: "Validation failed"**
- ✅ Use os dados de exemplo fornecidos
- ✅ Verifique se CPF/CNPJ são válidos
- ✅ Confirme formato de email e telefone

## 📋 **Dados de Teste**

### **Pessoa Física (CPF)**
```json
{
  "nome": "João Silva Santos",
  "documento": "12345678901",
  "dataNascimentoConstituicao": "1990-05-15",
  "rendaFaturamento": 5000.00,
  "telefone": "11987654321",
  "email": "joao.silva@email.com"
}
```

### **Pessoa Jurídica (CNPJ)**
```json
{
  "nome": "Empresa ABC Ltda",
  "documento": "11222333000181",
  "dataNascimentoConstituicao": "2020-01-15",
  "rendaFaturamento": 150000.00,
  "telefone": "1133334444",
  "email": "contato@empresaabc.com.br"
}
```

## 🎯 **Para o Tech Manager**

### **✅ O que Testar Durante a Entrevista**
1. **Funcionalidade Básica**: CRUD completo
2. **Validações**: CPF, CNPJ, Email, Telefone
3. **Regras de Negócio**: Soft delete, reativação
4. **Performance**: Tempo de resposta
5. **Tratamento de Erros**: Respostas adequadas

### **📊 Métricas a Observar**
- **Response Time**: Deve ser < 2000ms
- **Status Codes**: Respostas corretas
- **Validações**: Erros apropriados para dados inválidos
- **Consistência**: Mesmo ID retorna mesmo dado

### **🚀 Cenários de Teste**
1. **Happy Path**: Fluxo normal de criação/consulta
2. **Edge Cases**: Dados limítrofes
3. **Error Handling**: Dados inválidos
4. **Business Logic**: Soft delete e reativação

---

## 🎉 **Pronto para Testar!**

A collection está configurada para testar todos os aspectos da API:

✅ **Endpoints completos** com exemplos  
✅ **Dados válidos** para testes  
✅ **Validações** de regras de negócio  
✅ **Testes automáticos** incluídos  
✅ **Logs e debug** para troubleshooting  

**🚀 Importe no Postman e comece a testar!**

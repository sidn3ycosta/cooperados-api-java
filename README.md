# 🚀 Cooperados API - Java Spring Boot

API de Cadastro de Cooperados desenvolvida em Java 21 com Spring Boot 3.2.0, seguindo arquitetura hexagonal.

## 🚀 **Quick Start (5 minutos)**

```bash
# 1. Clone o projeto
git clone https://github.com/seu-usuario/cooperados-api-java.git
cd cooperados-api-java

# 2. Configure permissões
chmod +x docker-build.sh docker-cleanup.sh

# 3. Execute PostgreSQL (banco + migrations automáticas)
docker-compose up -d postgres

# 4. Execute a aplicação (Flyway cria as tabelas automaticamente)
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# 5. Verifique se está funcionando
curl http://localhost:8081/actuator/health

# 6. Teste os endpoints
curl http://localhost:8081/api/v1/cooperados
```

**🎉 Pronto! A API está rodando em http://localhost:8081**

**📝 O que acontece automaticamente:**
- ✅ **PostgreSQL** inicia com Docker
- ✅ **Flyway** executa as migrations (cria tabelas)
- ✅ **Aplicação** conecta ao banco
- ✅ **API** fica disponível para testes

## 📋 **Pré-requisitos**

- **Java 21**
- **Maven 3.8+**
- **Docker** (para PostgreSQL)

## 🏗️ **Estrutura do Projeto**

```
cooperados-api-java/
├── src/                    # 💻 Código fonte
├── docs/                   # 📚 Documentação detalhada
├── docker-compose.yml      # 🐳 PostgreSQL
├── Dockerfile.dev          # 🐳 Imagem Docker (opcional)
├── Makefile                # 🎯 Comandos úteis
├── pom.xml                 # 📦 Dependências Maven
└── README.md               # 📚 Este arquivo
```

## 🌐 **API Endpoints**

### Base URL: `http://localhost:8081/api/v1`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/cooperados` | Lista todos os cooperados |
| GET | `/cooperados/{id}` | Busca cooperado por ID |
| POST | `/cooperados` | Cria novo cooperado |
| PUT | `/cooperados/{id}` | Atualiza cooperado |
| DELETE | `/cooperados/{id}` | Remove cooperado |

## 🧪 **Testes**

```bash
# Executar testes
mvn test

# Executar com cobertura
mvn test jacoco:report
```

## 📱 **Testar API com Postman**

**Collection completa para testar todos os endpoints:**

1. **Baixe**: `Cooperados-API.postman_collection.json`
2. **Importe** no Postman
3. **Configure** `{{base_url}}` como `http://localhost:8081`
4. **Execute** os testes na ordem recomendada

**📖 Para instruções detalhadas**: [POSTMAN-README.md](POSTMAN-README.md)

## 🐳 **Comandos Docker Úteis**

```bash
# Apenas PostgreSQL (recomendado)
make postgres          # Inicia PostgreSQL
make postgres-down     # Para PostgreSQL

# Docker completo (opcional)
make dev               # Inicia tudo
make down              # Para tudo
make status            # Ver status
make logs              # Ver logs
make clean             # Limpa containers
```

## 🔄 **O que Acontece Automaticamente**

### **📦 Docker + Banco**
- **PostgreSQL** inicia automaticamente
- **Porta 5432** fica disponível
- **Banco `cooperados_db`** é criado
- **Usuário `cooperados_user`** é configurado

### **🏗️ Migrations (Flyway)**
- **Tabelas** são criadas automaticamente
- **Schema** é configurado
- **Dados de exemplo** são inseridos
- **Não precisa** configurar banco manualmente

### **🚀 Aplicação**
- **Conecta** ao PostgreSQL automaticamente
- **Valida** schema do banco
- **API** fica disponível em http://localhost:8081
- **Health check** em /actuator/health

## 🚨 **Problemas Comuns**

### **Porta já em uso**
```bash
# Verificar portas
lsof -i :8081
lsof -i :5432

# Parar serviços
docker-compose down
```

### **Banco não conecta**
```bash
# Verificar PostgreSQL
docker-compose ps postgres

# Ver logs
docker-compose logs postgres
```

### **Aplicação não inicia**
```bash
# Verificar configurações
cat src/main/resources/application-dev.yml

# Rebuild
mvn clean compile
```

## 📚 **Documentação Detalhada**

**Para mais detalhes, configurações avançadas e troubleshooting completo, consulte:**
**[docs/README.md](docs/README.md)**

## 🤝 **Contribuindo**

1. Fork o projeto
2. Crie uma branch para sua feature
3. Commit suas mudanças
4. Abra um Pull Request

## 📝 **Licença**

Este projeto está sob a licença MIT.

## 👥 **Autor**

- **Contribuidor** - Sidney Silva

---

**📖 Para documentação completa e detalhada: [docs/README.md](docs/README.md)**


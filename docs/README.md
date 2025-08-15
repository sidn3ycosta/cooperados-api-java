# 📚 Documentação Completa - Cooperados API - Java com Spring

**Guia completo e detalhado para desenvolvimento, configuração e troubleshooting.**

## 🎯 **Índice**

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Pré-requisitos Detalhados](#-pré-requisitos-detalhados)
- [Setup Completo](#-setup-completo)
- [Executando o Projeto](#-executando-o-projeto)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [API Endpoints Detalhados](#-api-endpoints-detalhados)
- [Testes](#-testes)
- [Docker](#-docker)
- [Troubleshooting](#-troubleshooting)
- [Contribuindo](#-contribuindo)

## 🎯 **Sobre o Projeto**

A Cooperados API é uma aplicação RESTful para gerenciamento de cooperados (pessoas físicas e jurídicas), desenvolvida como teste técnico demonstrando:

- **Arquitetura Hexagonal** (Clean Architecture)
- **Domain-Driven Design (DDD)**
- **Testes Unitários** com JUnit 5
- **Validações Customizadas** para CPF, CNPJ, Email e Telefone
- **Mapeamento de Objetos** com MapStruct
- **Migrações de Banco** com Flyway
- **Containerização** com Docker (PostgreSQL)

### Funcionalidades Principais

- ✅ Cadastro de cooperados (PF e PJ)
- ✅ Validação automática de documentos (CPF/CNPJ)
- ✅ Busca e filtros avançados
- ✅ Paginação e ordenação
- ✅ Soft delete e reativação
- ✅ Auditoria automática (criação/atualização)
- ✅ Health checks e monitoramento

## 🛠️ **Tecnologias Utilizadas**

### Backend
- **Java 21** - Linguagem de programação
- **Spring Boot 3.2.0** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Validation** - Validações
- **Spring Actuator** - Monitoramento

### Banco de Dados
- **PostgreSQL 15** - Banco de dados principal
- **Flyway** - Migrações de banco
- **HikariCP** - Pool de conexões

### Ferramentas
- **Maven** - Gerenciamento de dependências
- **MapStruct** - Mapeamento de objetos
- **JUnit 5** - Framework de testes
- **Docker** - PostgreSQL containerizado

### Validações Customizadas
- **CPF Validator** - Validação algorítmica de CPF
- **CNPJ Validator** - Validação algorítmica de CNPJ
- **Email Validator** - Validação de formato de email
- **Telefone Validator** - Validação de telefone brasileiro

## 📋 **Pré-requisitos Detalhados**

### Software Necessário
- **Java 21** (OpenJDK ou Oracle JDK)
- **Maven 3.8+**
- **Docker** (para PostgreSQL)
- **Git**

### Versões Recomendadas
```bash
# Verificar versões instaladas
java -version          # Java 21
mvn -version           # Maven 3.8+
docker --version       # Docker 20.10+
git --version          # Git 2.30+
```

## 🚀 **Setup Completo**

### 1. **Clone e Configuração Inicial**

```bash
# Clone o projeto
git clone https://github.com/seu-usuario/cooperados-api-java.git
cd cooperados-api-java

# Configure permissões
chmod +x docker-build.sh docker-cleanup.sh

# Verifique se está no branch correto
git branch
```

### 2. **Configurar Banco de Dados**

#### Opção A: PostgreSQL Docker (Recomendado)
```bash
# Iniciar apenas PostgreSQL
docker-compose up -d postgres

# Verificar se está rodando
docker-compose ps postgres

# Ver logs se necessário
docker-compose logs postgres
```

#### Opção B: PostgreSQL Local
```bash
# Criar banco de dados
createdb cooperados_db

# Criar usuário
createuser -P cooperados_user
# Senha: cooperados_pass

# Conceder privilégios
psql -d cooperados_db -c "GRANT ALL PRIVILEGES ON DATABASE cooperados_db TO cooperados_user;"
```

### 3. **Configurar Variáveis de Ambiente**

```bash
# Copiar arquivo de exemplo
cp env.example .env

# Editar configurações (opcional)
nano .env
```

**Configurações Padrão:**
```properties
# Banco de Dados
POSTGRES_DB=cooperados_db
POSTGRES_USER=cooperados_user
POSTGRES_PASSWORD=cooperados_pass
POSTGRES_HOST=localhost
POSTGRES_PORT=5432

# Aplicação
SPRING_PROFILES_ACTIVE=dev
SERVER_PORT=8081
JAVA_OPTS=-Xmx1g -Xms512m
```

## 🏃‍♂️ **Executando o Projeto**

### 1. **Setup Recomendado (PostgreSQL Docker + App Local)**

```bash
# Iniciar apenas PostgreSQL
docker-compose up -d postgres

# Verificar se está rodando
docker-compose ps postgres

# Executar a aplicação localmente
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

**A aplicação estará disponível em:**
- 🌐 **API**: http://localhost:8081
- 📊 **Health Check**: http://localhost:8081/actuator/health
- 📚 **Swagger**: http://localhost:8081/swagger-ui.html

### 2. **Alternativa: Tudo com Docker (opcional)**

```bash
# Build e execução completa
./docker-build.sh dev

# Ou usando Makefile
make dev

# Verificar status
make status
```

### 3. **Verificar Funcionamento**

```bash
# Health check
curl http://localhost:8081/actuator/health

# Testar API
curl http://localhost:8081/api/v1/cooperados

# Ver logs
docker-compose logs -f postgres
```

## 🏗️ **Estrutura do Projeto**

```
cooperados-api-java/
├── src/
│   ├── main/
│   │   ├── java/com/cooperados/
│   │   │   ├── application/          # Camada de Aplicação
│   │   │   │   ├── controller/       # Controllers REST
│   │   │   │   ├── dto/             # Data Transfer Objects
│   │   │   │   ├── service/          # Serviços de Aplicação
│   │   │   │   └── mapper/           # Mapeadores MapStruct
│   │   │   ├── domain/               # Camada de Domínio
│   │   │   │   ├── entity/           # Entidades de Domínio
│   │   │   │   ├── repository/       # Interfaces de Repositório
│   │   │   │   ├── service/          # Serviços de Domínio
│   │   │   │   └── valueobject/      # Objetos de Valor
│   │   │   └── infrastructure/       # Camada de Infraestrutura
│   │   │       ├── config/           # Configurações
│   │   │       ├── exception/        # Tratamento de Exceções
│   │   │       └── persistence/      # Implementações de Persistência
│   │   └── resources/
│   │       ├── application.yml       # Configuração principal
│   │       ├── application-dev.yml   # Configuração de desenvolvimento
│   │       └── db/migration/         # Migrações Flyway
│   └── test/                         # Testes
├── docs/                             # 📚 Documentação
├── docker-compose.yml                 # PostgreSQL
├── Dockerfile.dev                     # Imagem Docker (opcional)
├── pom.xml                           # Dependências Maven
└── README.md                         # Documentação principal
```

## 🌐 **API Endpoints Detalhados**

### Base URL
```
http://localhost:8081/api/v1
```

### Endpoints Disponíveis

#### 🔍 **Consultas (GET)**
| Endpoint | Descrição | Parâmetros |
|----------|-----------|------------|
| `/cooperados` | Lista todos os cooperados | `page`, `size`, `sort`, `direction` |
| `/cooperados/{id}` | Busca cooperado por ID | `id` (path) |
| `/cooperados/documento/{documento}` | Busca por CPF/CNPJ | `documento` (path) |
| `/cooperados/tipo/{tipo}` | Lista por tipo | `tipo` (CPF/CNPJ) |
| `/cooperados/ativos` | Lista apenas ativos | - |
| `/cooperados/existe/{documento}` | Verifica existência | `documento` (path) |
| `/cooperados/contar/tipo/{tipo}` | Conta por tipo | `tipo` (CPF/CNPJ) |

#### ➕ **Criação (POST)**
| Endpoint | Descrição | Body |
|----------|-----------|------|
| `/cooperados` | Cria novo cooperado | `CriarCooperadoRequest` |

#### ✏️ **Atualização (PUT)**
| Endpoint | Descrição | Parâmetros |
|----------|-----------|------------|
| `/cooperados/{id}` | Atualiza cooperado | `id` (path) + `AtualizarCooperadoRequest` |

#### 🗑️ **Exclusão (DELETE)**
| Endpoint | Descrição | Parâmetros |
|----------|-----------|------------|
| `/cooperados/{id}` | Remove cooperado (soft delete) | `id` (path) |

#### 🔄 **Operações Especiais (PATCH)**
| Endpoint | Descrição | Parâmetros |
|----------|-----------|------------|
| `/cooperados/{id}/reativar` | Reativa cooperado removido | `id` (path) |

### Exemplos de Uso

#### Listar Cooperados com Paginação
```bash
curl "http://localhost:8081/api/v1/cooperados?page=0&size=10&sort=nome&direction=asc"
```

#### Buscar por CPF
```bash
curl "http://localhost:8081/api/v1/cooperados/documento/12345678901"
```

#### Criar Novo Cooperado
```bash
curl -X POST "http://localhost:8081/api/v1/cooperados" \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "documento": "12345678901",
    "dataNascimentoConstituicao": "1990-05-15",
    "rendaFaturamento": 5000.00,
    "telefone": "11987654321",
    "email": "joao@email.com"
  }'
```

## 🧪 **Testes**

### Executar Todos os Testes
```bash
# Testes unitários
mvn test

# Testes com cobertura
mvn test jacoco:report

# Testes específicos
mvn test -Dtest=CnpjValidatorTest
```

### Executar Testes com Docker
```bash
# Ambiente de teste isolado
make test-docker

# Ou usando docker-compose
docker-compose -f docker-compose.yml -f docker-compose.test.yml --profile test up --abort-on-container-exit
```

### Estrutura de Testes
```
src/test/java/com/cooperados/
├── application/
│   └── dto/                          # Testes dos DTOs
├── domain/                            # Testes de domínio
├── infrastructure/
│   └── exception/                     # Testes de exceções
└── shared/
    └── validation/                    # Testes dos validadores
```

## 🐳 **Docker**

### Comandos Rápidos
```bash
# Desenvolvimento
make dev

# Produção
make prod

# Parar serviços
make down

# Ver logs
make logs

# Limpeza
make clean
```

### Scripts Disponíveis
```bash
# Build e execução automática
./docker-build.sh dev      # Desenvolvimento
./docker-build.sh prod     # Produção

# Limpeza
./docker-cleanup.sh containers    # Remove containers
./docker-cleanup.sh all           # Limpeza completa
```

### Serviços Docker
| Serviço | Porta | Descrição |
|---------|-------|-----------|
| `cooperados-api` | 8080 | API principal |
| `cooperados-api-dev` | 8081 | API desenvolvimento |
| `postgres` | 5432 | Banco de dados |
| `pgadmin` | 5050 | Interface PostgreSQL |

## 🔧 **Troubleshooting**

### Problemas Comuns

#### 1. **Porta já em uso**
```bash
# Verificar portas em uso
lsof -i :8081
lsof -i :5432

# Parar serviços conflitantes
docker-compose down
```

#### 2. **Banco não conecta**
```bash
# Verificar se PostgreSQL está rodando
docker-compose ps postgres

# Verificar logs
docker-compose logs postgres

# Testar conexão
docker exec -it cooperados-postgres psql -U cooperados_user -d cooperados_db
```

#### 3. **Aplicação não inicia**
```bash
# Verificar logs
tail -f app.log

# Verificar configurações
cat src/main/resources/application-dev.yml

# Rebuild
mvn clean compile
```

#### 4. **Testes falhando**
```bash
# Limpar e recompilar
mvn clean compile test

# Verificar banco de teste
docker-compose -f docker-compose.test.yml ps
```

### Comandos de Debug
```bash
# Status dos serviços
make status

# Logs em tempo real
make logs

# Shell na aplicação
make debug

# Shell no banco
make debug-db
```

## 🤝 **Contribuindo**

### 1. Fork o Projeto
1. Faça fork do repositório
2. Clone seu fork localmente
3. Crie uma branch para sua feature

### 2. Desenvolvimento
```bash
# Criar branch
git checkout -b feature/nova-funcionalidade

# Fazer alterações
# ... código ...

# Commit
git add .
git commit -m "feat: adiciona nova funcionalidade"

# Push
git push origin feature/nova-funcionalidade
```

### 3. Pull Request
1. Abra um Pull Request
2. Descreva as mudanças
3. Aguarde review

### 4. Padrões de Commit
```
feat: nova funcionalidade
fix: correção de bug
docs: documentação
style: formatação
refactor: refatoração
test: testes
chore: tarefas de manutenção
```

## 📝 **Licença**

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👥 **Autor**

- **Contribuidor** - Sidney Silva

---

## 🚀 **Pronto para Desenvolver!**

Agora você tem tudo configurado para começar a desenvolver na Cooperados API.

**🎯 Setup recomendado para localhost:**
1. Execute `make postgres` para iniciar PostgreSQL
2. Execute `mvn spring-boot:run -Dspring-boot.run.profiles=dev` para a aplicação
3. Acesse http://localhost:8081/actuator/health para verificar
4. Teste os endpoints da API
5. Comece a desenvolver suas features!

**📞 Ajuda?**
- Verifique a seção [Troubleshooting](#-troubleshooting)
- Consulte os logs: `make logs`
- Verifique o status: `make status`


# 🎯 Makefile - Cooperados API
# Comandos simplificados para desenvolvimento local

.PHONY: help dev down status logs clean postgres postgres-down

# 🚀 Comandos principais
help: ## Mostra esta ajuda
	@echo "🎯 Cooperados API - Comandos disponíveis:"
	@echo ""
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "  \033[36m%-20s\033[0m %s\n", $$1, $$2}'
	@echo ""
	@echo "📖 Para mais detalhes, consulte a documentação em docs/"

# 🐳 Docker completo (opcional)
dev: ## Inicia ambiente completo com Docker
	@echo "🚀 Iniciando ambiente completo..."
	docker-compose up -d
	@echo "✅ Ambiente iniciado! API disponível em http://localhost:8081"

down: ## Para todos os serviços
	@echo "🛑 Parando todos os serviços..."
	docker-compose down
	@echo "✅ Serviços parados"

# 🎯 PostgreSQL apenas (recomendado para desenvolvimento)
postgres: ## Inicia apenas PostgreSQL
	@echo "🐘 Iniciando PostgreSQL..."
	docker-compose up -d postgres
	@echo "✅ PostgreSQL iniciado! Execute: mvn spring-boot:run -Dspring-boot.run.profiles=dev"

postgres-down: ## Para PostgreSQL
	@echo "🛑 Parando PostgreSQL..."
	docker-compose stop postgres
	@echo "✅ PostgreSQL parado"

# 📊 Monitoramento
status: ## Mostra status dos serviços
	@echo "📊 Status dos serviços:"
	docker-compose ps

logs: ## Mostra logs em tempo real
	@echo "📝 Logs em tempo real (Ctrl+C para sair):"
	docker-compose logs -f

# 🧹 Limpeza
clean: ## Remove containers parados
	@echo "🧹 Limpando containers parados..."
	docker-compose down --remove-orphans
	docker container prune -f
	@echo "✅ Limpeza concluída"

clean-all: ## Limpeza completa (containers + volumes)
	@echo "🧹 Limpeza completa..."
	docker-compose down -v --remove-orphans
	docker container prune -f
	docker volume prune -f
	@echo "✅ Limpeza completa concluída"

# 🔧 Desenvolvimento
build: ## Compila o projeto
	@echo "🔨 Compilando projeto..."
	mvn clean compile
	@echo "✅ Compilação concluída"

test: ## Executa testes
	@echo "🧪 Executando testes..."
	mvn test
	@echo "✅ Testes concluídos"

run: ## Executa aplicação localmente
	@echo "🚀 Executando aplicação..."
	mvn spring-boot:run -Dspring-boot.run.profiles=dev

# 🐛 Debug
debug-db: ## Shell no PostgreSQL
	@echo "🐘 Conectando ao PostgreSQL..."
	docker exec -it cooperados-postgres psql -U cooperados_user -d cooperados_db

# 📚 Documentação
docs: ## Abre documentação
	@echo "📚 Abrindo documentação..."
	open docs/README.md

# 🆘 Ajuda rápida
quick-start: ## Setup rápido em 5 minutos
	@echo "⚡ Setup rápido em 5 minutos:"
	@echo "1. make postgres          # Inicia PostgreSQL"
	@echo "2. mvn spring-boot:run    # Executa aplicação"
	@echo "3. curl http://localhost:8081/actuator/health"
	@echo ""
	@echo "📖 Para mais detalhes: docs/04-quick-start.md"

# 🎯 Comandos úteis para desenvolvimento
dev-setup: ## Setup completo para desenvolvimento
	@echo "🔧 Setup para desenvolvimento:"
	@echo "1. Iniciando PostgreSQL..."
	@make postgres
	@echo ""
	@echo "2. Execute a aplicação:"
	@echo "   mvn spring-boot:run -Dspring-boot.run.profiles=dev"
	@echo ""
	@echo "3. Verifique funcionamento:"
	@echo "   curl http://localhost:8081/actuator/health"
	@echo ""
	@echo "🎉 Pronto para desenvolver!"

# 📋 Informações do projeto
info: ## Informações do projeto
	@echo "📋 Cooperados API - Informações:"
	@echo "🌐 API: http://localhost:8081"
	@echo "📊 Health: http://localhost:8081/actuator/health"
	@echo "🐘 PostgreSQL: localhost:5432"
	@echo ""
	@echo "📖 Documentação: docs/"
	@echo "🚀 Quick Start: make quick-start"

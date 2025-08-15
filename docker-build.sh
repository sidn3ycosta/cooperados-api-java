#!/bin/bash

# Script para build e execução da Cooperados API com Docker
# Uso: ./docker-build.sh [dev|prod]

set -e

# Cores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Função para imprimir mensagens coloridas
print_message() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

print_header() {
    echo -e "${BLUE}================================${NC}"
    echo -e "${BLUE}  Cooperados API - Docker Build${NC}"
    echo -e "${BLUE}================================${NC}"
}

# Verifica se o Docker está rodando
check_docker() {
    if ! docker info > /dev/null 2>&1; then
        print_error "Docker não está rodando. Inicie o Docker e tente novamente."
        exit 1
    fi
    print_message "Docker está rodando"
}

# Build da aplicação
build_app() {
    print_message "Fazendo build da aplicação com Maven..."
    
    if ! mvn clean package -DskipTests; then
        print_error "Falha no build da aplicação"
        exit 1
    fi
    
    print_message "Build da aplicação concluído com sucesso"
}

# Build da imagem Docker
build_docker_image() {
    local profile=${1:-prod}
    
    print_message "Fazendo build da imagem Docker ($profile)..."
    
    if [ "$profile" = "dev" ]; then
        docker build -f Dockerfile.dev -t cooperados-api:dev .
    else
        docker build -t cooperados-api:latest .
    fi
    
    print_message "Imagem Docker criada com sucesso"
}

# Executa com docker-compose
run_docker_compose() {
    local profile=${1:-prod}
    
    print_message "Iniciando serviços com docker-compose ($profile)..."
    
    if [ "$profile" = "dev" ]; then
        docker-compose --profile dev up -d
    else
        docker-compose up -d
    fi
    
    print_message "Serviços iniciados com sucesso"
}

# Mostra status dos serviços
show_status() {
    print_message "Status dos serviços:"
    docker-compose ps
    
    echo ""
    print_message "Logs da aplicação:"
    docker-compose logs --tail=20 cooperados-api
}

# Função principal
main() {
    local profile=${1:-prod}
    
    print_header
    
    # Validação do parâmetro
    if [ "$profile" != "dev" ] && [ "$profile" != "prod" ]; then
        print_error "Perfil inválido. Use 'dev' ou 'prod'"
        echo "Uso: $0 [dev|prod]"
        exit 1
    fi
    
    print_message "Perfil selecionado: $profile"
    
    # Verificações
    check_docker
    
    # Build
    build_app
    build_docker_image "$profile"
    
    # Execução
    run_docker_compose "$profile"
    
    # Aguarda um pouco para os serviços iniciarem
    print_message "Aguardando serviços iniciarem..."
    sleep 10
    
    # Status
    show_status
    
    echo ""
    print_message "✅ Cooperados API está rodando!"
    
    if [ "$profile" = "prod" ]; then
        echo "🌐 API: http://localhost:8080"
        echo "📊 Health: http://localhost:8080/actuator/health"
    else
        echo "🌐 API Dev: http://localhost:8081"
        echo "📊 Health Dev: http://localhost:8081/actuator/health"
        echo "🗄️  pgAdmin: http://localhost:5050 (admin@cooperados.com / admin123)"
    fi
    
    echo "🗄️  PostgreSQL: localhost:5432"
    echo ""
    print_message "Para parar os serviços: docker-compose down"
}

# Executa o script
main "$@"

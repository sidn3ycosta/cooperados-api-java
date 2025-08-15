#!/bin/bash

# Script para limpeza dos containers e imagens Docker da Cooperados API
# Uso: ./docker-cleanup.sh [all|containers|images|volumes]

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
    echo -e "${BLUE}  Cooperados API - Docker Cleanup${NC}"
    echo -e "${BLUE}================================${NC}"
}

# Para e remove containers
cleanup_containers() {
    print_message "Parando e removendo containers..."
    
    # Para todos os serviços
    docker-compose down --remove-orphans
    
    # Remove containers órfãos
    docker container prune -f
    
    print_message "Containers limpos com sucesso"
}

# Remove imagens
cleanup_images() {
    print_message "Removendo imagens Docker..."
    
    # Remove imagens da aplicação
    docker rmi cooperados-api:latest cooperados-api:dev 2>/dev/null || true
    
    # Remove imagens não utilizadas
    docker image prune -f
    
    print_message "Imagens limpas com sucesso"
}

# Remove volumes
cleanup_volumes() {
    print_message "Removendo volumes..."
    
    # Remove volumes específicos
    docker volume rm cooperados-api-java_postgres_data 2>/dev/null || true
    
    # Remove volumes não utilizados
    docker volume prune -f
    
    print_message "Volumes limpos com sucesso"
}

# Remove redes
cleanup_networks() {
    print_message "Removendo redes..."
    
    # Remove redes não utilizadas
    docker network prune -f
    
    print_message "Redes limpas com sucesso"
}

# Limpeza completa
cleanup_all() {
    print_warning "⚠️  ATENÇÃO: Esta operação irá remover TODOS os dados!"
    read -p "Tem certeza que deseja continuar? (y/N): " -n 1 -r
    echo
    
    if [[ $REPLY =~ ^[Yy]$ ]]; then
        print_message "Executando limpeza completa..."
        
        cleanup_containers
        cleanup_images
        cleanup_volumes
        cleanup_networks
        
        print_message "✅ Limpeza completa concluída!"
    else
        print_message "Operação cancelada pelo usuário"
    fi
}

# Função principal
main() {
    local action=${1:-containers}
    
    print_header
    
    case $action in
        "all")
            cleanup_all
            ;;
        "containers")
            cleanup_containers
            ;;
        "images")
            cleanup_images
            ;;
        "volumes")
            cleanup_volumes
            ;;
        "networks")
            cleanup_networks
            ;;
        *)
            print_error "Ação inválida: $action"
            echo "Uso: $0 [all|containers|images|volumes|networks]"
            echo ""
            echo "Ações disponíveis:"
            echo "  all       - Limpeza completa (remove tudo)"
            echo "  containers - Remove apenas containers"
            echo "  images    - Remove apenas imagens"
            echo "  volumes   - Remove apenas volumes"
            echo "  networks  - Remove apenas redes"
            exit 1
            ;;
    esac
    
    echo ""
    print_message "Limpeza concluída com sucesso!"
}

# Executa o script
main "$@"

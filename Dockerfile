# Dockerfile para Cooperados API
FROM openjdk:21-jdk-slim

# Informações do projeto
LABEL maintainer="Cooperados Team"
LABEL version="1.0.0"
LABEL description="API de Cadastro de Cooperados"

# Define o diretório de trabalho
WORKDIR /app

# Instala dependências do sistema
RUN apt-get update && apt-get install -y \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Copia o arquivo JAR da aplicação
COPY target/cooperados-api-1.0.0.jar app.jar

# Cria usuário não-root para segurança
RUN groupadd -r cooperados && useradd -r -g cooperados cooperados
RUN chown -R cooperados:cooperados /app
USER cooperados

# Expõe a porta da aplicação
EXPOSE 8080

# Define variáveis de ambiente
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENV SPRING_PROFILES_ACTIVE="prod"

# Healthcheck para verificar se a aplicação está funcionando
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Comando para executar a aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

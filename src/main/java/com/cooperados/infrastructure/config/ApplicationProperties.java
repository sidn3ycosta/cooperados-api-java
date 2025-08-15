package com.cooperados.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Propriedades de configuração da aplicação
 * 
 * @author Cooperados Team
 * @version 1.0.0
 */
@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {

    private String name = "Cooperados API";
    private String version = "1.0.0";
    private String description = "API de Cadastro de Cooperados - Teste Técnico";
    
    private Database database = new Database();
    private Security security = new Security();

    // Getters e Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Database getDatabase() { return database; }
    public void setDatabase(Database database) { this.database = database; }

    public Security getSecurity() { return security; }
    public void setSecurity(Security security) { this.security = security; }

    /**
     * Configurações de banco de dados
     */
    public static class Database {
        private String url;
        private String username;
        private String password;
        private String driverClassName;
        private int maxPoolSize = 20;
        private int minIdle = 5;
        private long connectionTimeout = 30000;
        private long idleTimeout = 600000;
        private long maxLifetime = 1800000;

        // Getters e Setters
        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public String getDriverClassName() { return driverClassName; }
        public void setDriverClassName(String driverClassName) { this.driverClassName = driverClassName; }

        public int getMaxPoolSize() { return maxPoolSize; }
        public void setMaxPoolSize(int maxPoolSize) { this.maxPoolSize = maxPoolSize; }

        public int getMinIdle() { return minIdle; }
        public void setMinIdle(int minIdle) { this.minIdle = minIdle; }

        public long getConnectionTimeout() { return connectionTimeout; }
        public void setConnectionTimeout(long connectionTimeout) { this.connectionTimeout = connectionTimeout; }

        public long getIdleTimeout() { return idleTimeout; }
        public void setIdleTimeout(long idleTimeout) { this.idleTimeout = idleTimeout; }

        public long getMaxLifetime() { return maxLifetime; }
        public void setMaxLifetime(long maxLifetime) { this.maxLifetime = maxLifetime; }
    }

    /**
     * Configurações de segurança
     */
    public static class Security {
        private boolean enabled = false;
        private String jwtSecret = "defaultSecretKey";
        private long jwtExpiration = 86400000; // 24 horas em millisegundos
        private String[] allowedOrigins = {"*"};

        // Getters e Setters
        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }

        public String getJwtSecret() { return jwtSecret; }
        public void setJwtSecret(String jwtSecret) { this.jwtSecret = jwtSecret; }

        public long getJwtExpiration() { return jwtExpiration; }
        public void setJwtExpiration(long jwtExpiration) { this.jwtExpiration = jwtExpiration; }

        public String[] getAllowedOrigins() { return allowedOrigins; }
        public void setAllowedOrigins(String[] allowedOrigins) { this.allowedOrigins = allowedOrigins; }
    }
}

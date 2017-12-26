package com.harry.winser.api.configuration;

import com.harry.winser.docker.secrets.DockerSecretsException;
import com.harry.winser.docker.secrets.DockerSecretsLoader;
import com.harry.winser.docker.secrets.DockerSecretsLoaderBuilder;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;


@Configuration
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource() throws DockerSecretsException {

        Map<String, String> secrets = new DockerSecretsLoaderBuilder().build().loadAsMap();

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(secrets.get("spring.datasource.url"));
        dataSource.setUsername(secrets.get("spring.datasource.username"));
        dataSource.setPassword(secrets.get("spring.datasource.password"));
        dataSource.setMaxLifetime(60000);
        dataSource.setMaximumPoolSize(8);
        dataSource.setConnectionTimeout(30000);
        dataSource.setConnectionTestQuery("SELECT 1");
        dataSource.setPoolName("SpringBootHikariCP");
        dataSource.setMaxLifetime(2000000);
        dataSource.setMinimumIdle(5);
        dataSource.setIdleTimeout(30000);

        return dataSource;
    }
}

package com.harry.winser.api.configuration;

import com.harry.winser.docker.secrets.DockerSecretsException;
import com.harry.winser.docker.secrets.DockerSecretsLoader;
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

        Map<String, String> secrets = new DockerSecretsLoader().loadAsMap();

        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(secrets.get("spring.datasource.url"));
        ds.setUsername(secrets.get("spring.datasource.username"));
        ds.setPassword(secrets.get("spring.datasource.password"));

        return ds;
    }
}

package com.harry.winser.api.configuration;

import com.harry.winser.docker.secrets.DockerSecretsException;
import com.harry.winser.docker.secrets.DockerSecretsLoader;
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

        return DataSourceBuilder
                .create()
                .username(secrets.get("spring.datasource.username"))
                .password(secrets.get("spring.datasource.password"))
                .url(secrets.get("spring.datasource.url"))
                .driverClassName("com.mysql.jdbc.Driver")
                .build();
    }
}

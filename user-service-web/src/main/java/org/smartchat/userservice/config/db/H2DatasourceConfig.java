package org.smartchat.userservice.config.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("h2")
public class H2DatasourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "datasource.test.h2")
    public DataSource h2Datasource() {
        return DataSourceBuilder
                .create()
                .url("jdbc:h2:mem:dataSource;DB_CLOSE_ON_EXIT=FALSE")
                .username("sa")
                .driverClassName("org.h2.Driver")
                .build();
    }
}

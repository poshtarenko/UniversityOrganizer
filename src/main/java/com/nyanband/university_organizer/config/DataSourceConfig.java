package com.nyanband.university_organizer.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://dpg-cfpo8to2i3mo4bs6i880-a.frankfurt-postgres.render.com:5432/nyanbanddb")
                .username("nyanuser")
                .password("RdVD2REcxH2HPeECCgNYiok0Ebpdm7jK")
                .build();
    }
}

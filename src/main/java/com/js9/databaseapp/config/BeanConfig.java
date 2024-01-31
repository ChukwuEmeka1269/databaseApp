package com.js9.databaseapp.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
public class BeanConfig {

    private final DataSource dataSource;

    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }
}

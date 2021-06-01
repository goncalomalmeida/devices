package com.hardware.persistence.configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootConfiguration
@ComponentScan(value = "com.hardware.persistence.impl")
@EntityScan("com.hardware.persistence.impl.entities")
@EnableJpaRepositories(
        basePackages = "com.hardware.persistence.impl.repositories"
)
@EnableTransactionManagement
@EnableAutoConfiguration
public class PersistenceConfiguration {

}

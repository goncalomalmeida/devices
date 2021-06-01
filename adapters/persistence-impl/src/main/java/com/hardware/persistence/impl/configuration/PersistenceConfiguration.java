package com.hardware.persistence.impl.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.hardware.persistence.impl.entities")
@EnableJpaRepositories(
        basePackages = "com.hardware.persistence.impl.repositories"
)
public class PersistenceConfiguration {
}

package com.hardware.devices.configurations;

import com.hardware.domain.api.CreateDeviceUseCase;
import com.hardware.domain.impl.CreateDeviceUseCaseImpl;
import com.hardware.persistence.api.DeviceDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public CreateDeviceUseCase createDeviceUseCase(DeviceDAO deviceDAO) {

        return new CreateDeviceUseCaseImpl(deviceDAO);
    }

}

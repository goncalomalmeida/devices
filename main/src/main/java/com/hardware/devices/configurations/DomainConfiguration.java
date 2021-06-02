package com.hardware.devices.configurations;

import com.hardware.domain.api.CreateDeviceUseCase;
import com.hardware.domain.impl.CreateDeviceUseCaseImpl;
import com.hardware.domain.impl.factories.DeviceFactory;
import com.hardware.persistence.api.DeviceDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public CreateDeviceUseCase createDeviceUseCase(DeviceDAO deviceDAO, DeviceFactory deviceFactory) {

        return new CreateDeviceUseCaseImpl(deviceDAO, deviceFactory);
    }

    @Bean
    public DeviceFactory deviceFactory() {

        return new DeviceFactory();
    }
}

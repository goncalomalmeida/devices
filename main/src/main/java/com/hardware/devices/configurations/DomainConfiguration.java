package com.hardware.devices.configurations;

import com.hardware.domain.api.CreateDeviceUseCase;
import com.hardware.domain.api.DeleteDeviceUseCase;
import com.hardware.domain.api.GetDeviceUseCase;
import com.hardware.domain.api.ListDevicesUseCase;
import com.hardware.domain.api.UpdateDeviceUseCase;
import com.hardware.domain.impl.DeviceUseCaseImpl;
import com.hardware.domain.impl.factories.DeviceFactory;
import com.hardware.persistence.api.DeviceDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public CreateDeviceUseCase createDeviceUseCase(DeviceDAO deviceDAO, DeviceFactory deviceFactory) {

        return new DeviceUseCaseImpl(deviceDAO, deviceFactory);
    }

    @Bean
    public DeviceFactory deviceFactory() {

        return new DeviceFactory();
    }

    @Bean
    public GetDeviceUseCase getDeviceUseCase(DeviceDAO deviceDAO, DeviceFactory deviceFactory) {

        return new DeviceUseCaseImpl(deviceDAO, deviceFactory);
    }

    @Bean
    public ListDevicesUseCase listDevicesUseCase(DeviceDAO deviceDAO, DeviceFactory deviceFactory) {

        return new DeviceUseCaseImpl(deviceDAO, deviceFactory);
    }

    @Bean
    public UpdateDeviceUseCase updateDeviceUseCase(DeviceDAO deviceDAO, DeviceFactory deviceFactory) {

        return new DeviceUseCaseImpl(deviceDAO, deviceFactory);
    }

    @Bean
    public DeleteDeviceUseCase deleteDeviceUseCase(DeviceDAO deviceDAO, DeviceFactory deviceFactory) {

        return new DeviceUseCaseImpl(deviceDAO, deviceFactory);
    }
}

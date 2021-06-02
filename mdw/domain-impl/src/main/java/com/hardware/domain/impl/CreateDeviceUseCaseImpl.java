package com.hardware.domain.impl;

import com.hardware.domain.api.CreateDeviceUseCase;
import com.hardware.domain.catalog.Device;
import com.hardware.domain.catalog.DeviceCreationRequest;
import com.hardware.domain.impl.factories.DeviceFactory;
import com.hardware.persistence.api.DeviceDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CreateDeviceUseCaseImpl implements CreateDeviceUseCase {

    private final DeviceDAO deviceDAO;

    private final DeviceFactory deviceFactory;

    @Override
    public Device create(DeviceCreationRequest deviceCreationRequest) {

        final Device device = deviceFactory.instantiate(deviceCreationRequest.getName(), deviceCreationRequest.getBrand());

        return deviceDAO.create(device);
    }
}

package com.hardware.domain.impl;

import com.hardware.domain.api.CreateDeviceUseCase;
import com.hardware.domain.api.GetDeviceUseCase;
import com.hardware.domain.catalog.Device;
import com.hardware.domain.catalog.DeviceCreationRequest;
import com.hardware.domain.impl.factories.DeviceFactory;
import com.hardware.persistence.api.DeviceDAO;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DeviceUseCaseImpl implements CreateDeviceUseCase, GetDeviceUseCase {

    private final DeviceDAO deviceDAO;

    private final DeviceFactory deviceFactory;

    @Override
    public Device create(DeviceCreationRequest deviceCreationRequest) {

        final Device device = deviceFactory.instantiate(deviceCreationRequest.getName(), deviceCreationRequest.getBrand());

        return deviceDAO.create(device);
    }

    @Override
    public Optional<Device> findById(long id) {

        return deviceDAO.findById(id);
    }
}

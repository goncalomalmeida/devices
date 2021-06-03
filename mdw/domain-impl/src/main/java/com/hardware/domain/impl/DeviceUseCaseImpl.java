package com.hardware.domain.impl;

import com.hardware.domain.api.CreateDeviceUseCase;
import com.hardware.domain.api.GetDeviceUseCase;
import com.hardware.domain.api.ListDevicesUseCase;
import com.hardware.domain.catalog.Device;
import com.hardware.domain.catalog.DeviceCreationRequest;
import com.hardware.domain.catalog.Page;
import com.hardware.domain.impl.factories.DeviceFactory;
import com.hardware.persistence.api.DeviceDAO;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DeviceUseCaseImpl implements CreateDeviceUseCase, GetDeviceUseCase, ListDevicesUseCase {

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

    @Override
    public List<Device> findAll(Page page) {

        return deviceDAO.findAll(page);
    }
}

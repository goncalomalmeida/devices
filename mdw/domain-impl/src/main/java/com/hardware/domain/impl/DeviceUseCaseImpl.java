package com.hardware.domain.impl;

import com.hardware.domain.api.CreateDeviceUseCase;
import com.hardware.domain.api.DeleteDeviceUseCase;
import com.hardware.domain.api.GetDeviceUseCase;
import com.hardware.domain.api.ListDevicesUseCase;
import com.hardware.domain.api.UpdateDeviceUseCase;
import com.hardware.domain.catalog.Device;
import com.hardware.domain.catalog.DeviceRequest;
import com.hardware.domain.catalog.Page;
import com.hardware.domain.catalog.UpdateOperationOutcome;
import com.hardware.domain.catalog.exceptions.NotFoundException;
import com.hardware.domain.impl.factories.DeviceFactory;
import com.hardware.persistence.api.DeviceDAO;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DeviceUseCaseImpl implements CreateDeviceUseCase,
                                          GetDeviceUseCase,
                                          ListDevicesUseCase,
                                          DeleteDeviceUseCase,
                                          UpdateDeviceUseCase {

    private final DeviceDAO deviceDAO;

    private final DeviceFactory deviceFactory;

    @Override
    public Device create(DeviceRequest deviceRequest) {

        return createInternal(null, deviceRequest);
    }

    @Override
    public Optional<Device> findById(long id) {

        return deviceDAO.findById(id);
    }

    @Override
    public List<Device> findAll(Page page, String brand) {

        return deviceDAO.findAll(page, brand);
    }

    @Override
    public void delete(long id) {

        deviceDAO.delete(id);
    }

    @Override
    public UpdateOperationOutcome update(long id, DeviceRequest deviceRequest) {

        UpdateOperationOutcome result;

        final Optional<Device> existingDevice = findById(id);

        if (existingDevice.isEmpty()) {
            createInternal(id, deviceRequest);
            result = UpdateOperationOutcome.CREATED;
        } else {
            final Device updatedDevice = existingDevice.get().toBuilder()
                    .name(deviceRequest.getName())
                    .brand(deviceRequest.getBrand())
                    .build();
            deviceDAO.update(updatedDevice);
            result = UpdateOperationOutcome.UPDATED;
        }

        return result;
    }

    @Override
    public Device partialUpdate(long id, DeviceRequest deviceRequest) {

        return findById(id)
                .map(device -> merge(device, deviceRequest))
                .map(deviceDAO::update)
                .orElseThrow(NotFoundException::deviceNotFoundException);
    }

    private Device merge(Device device, DeviceRequest deviceRequest) {

        return device.toBuilder()
                .brand(Optional.ofNullable(deviceRequest.getBrand()).orElse(device.getBrand()))
                .name(Optional.ofNullable(deviceRequest.getName()).orElse(device.getName()))
                .build();
    }

    private Device createInternal(Long predefinedId, DeviceRequest deviceRequest) {

        final Device device = deviceFactory.instantiate(predefinedId, deviceRequest.getName(), deviceRequest.getBrand());

        return deviceDAO.create(device);
    }
}

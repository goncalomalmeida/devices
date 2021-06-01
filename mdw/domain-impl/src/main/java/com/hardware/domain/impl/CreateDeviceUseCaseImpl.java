package com.hardware.domain.impl;

import com.hardware.domain.api.CreateDeviceUseCase;
import com.hardware.domain.catalog.Device;
import com.hardware.persistence.api.DeviceDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CreateDeviceUseCaseImpl implements CreateDeviceUseCase {

    private final DeviceDAO deviceDAO;

    @Override
    public Device create(Device device) {

        // TODO
        return null;
    }
}

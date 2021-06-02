package com.hardware.domain.api;

import com.hardware.domain.catalog.Device;

import java.util.Optional;

public interface GetDeviceUseCase {

    // TODO add javadoc
    Optional<Device> findById(long id);
}

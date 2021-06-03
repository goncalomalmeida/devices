package com.hardware.domain.api;

import com.hardware.domain.catalog.Device;
import com.hardware.domain.catalog.DeviceRequest;

public interface CreateDeviceUseCase {

    // TODO add javadoc
    Device create(DeviceRequest deviceRequest);
}

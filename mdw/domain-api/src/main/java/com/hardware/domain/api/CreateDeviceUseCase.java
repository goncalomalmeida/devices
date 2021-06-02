package com.hardware.domain.api;

import com.hardware.domain.catalog.Device;
import com.hardware.domain.catalog.DeviceCreationRequest;

public interface CreateDeviceUseCase {

    // TODO add javadoc
    Device create(DeviceCreationRequest deviceCreationRequest);
}

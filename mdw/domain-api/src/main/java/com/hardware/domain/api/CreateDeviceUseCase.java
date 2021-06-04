package com.hardware.domain.api;

import com.hardware.domain.catalog.Device;
import com.hardware.domain.catalog.DeviceRequest;

public interface CreateDeviceUseCase {

    /**
     * Creates a device using the incoming data and returns the created entity.
     *
     * @param deviceRequest - relevant data for creating a device
     * @return a newly created {@link Device}
     */
    Device create(DeviceRequest deviceRequest);
}

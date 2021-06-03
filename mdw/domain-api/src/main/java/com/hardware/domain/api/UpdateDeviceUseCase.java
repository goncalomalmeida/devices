package com.hardware.domain.api;

import com.hardware.domain.catalog.Device;
import com.hardware.domain.catalog.DeviceRequest;
import com.hardware.domain.catalog.UpdateOperationOutcome;

public interface UpdateDeviceUseCase {

    // TODO add javadoc
    UpdateOperationOutcome update(long id, DeviceRequest deviceRequest);

    Device partialUpdate(long id, DeviceRequest deviceRequest);
}

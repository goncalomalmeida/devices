package com.hardware.domain.api;

import com.hardware.domain.catalog.Device;

public interface DeleteDeviceUseCase {

    /**
     * Deletes a device if it exists or throws an exception otherwise.
     *
     * @param id - the {@link Device} identifier
     */
    void delete(long id);
}

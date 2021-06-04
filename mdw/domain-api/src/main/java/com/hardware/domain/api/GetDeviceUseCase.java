package com.hardware.domain.api;

import com.hardware.domain.catalog.Device;

import java.util.Optional;

public interface GetDeviceUseCase {

    /**
     * Finds a {@link Device} by its identifier.
     *
     * @param id - the {@link Device} identifier
     * @return it may or not return a {@link Device} depending if it exists
     */
    Optional<Device> findById(long id);
}

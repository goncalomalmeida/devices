package com.hardware.persistence.api;

import com.hardware.domain.catalog.Device;

import java.util.Optional;

public interface DeviceDAO {

    Device create(Device device);

    Optional<Device> findById(long id);
}

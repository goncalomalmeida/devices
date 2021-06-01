package com.hardware.persistence.api;

import com.hardware.domain.catalog.Device;

public interface DeviceDAO {

    Device create(Device device);
}

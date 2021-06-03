package com.hardware.domain.impl.factories;

import com.hardware.domain.catalog.Device;

import java.time.Instant;

public class DeviceFactory {

    public Device instantiate(Long id, String name, String brand) {

        return new Device(id, name, brand, Instant.now());
    }
}

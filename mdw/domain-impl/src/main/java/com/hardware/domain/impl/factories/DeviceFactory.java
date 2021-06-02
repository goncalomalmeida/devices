package com.hardware.domain.impl.factories;

import com.hardware.domain.catalog.Device;

import java.time.Instant;

public class DeviceFactory {

    public Device instantiate(String name, String brand) {

        return new Device(null, name, brand, Instant.now());
    }
}

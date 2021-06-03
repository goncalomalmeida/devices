package com.hardware.persistence.api;

import com.hardware.domain.catalog.Device;
import com.hardware.domain.catalog.Page;

import java.util.List;
import java.util.Optional;

public interface DeviceDAO {

    Device create(Device device);

    Optional<Device> findById(long id);

    List<Device> findAll(Page page, String brand);

    void delete(long id);

    Device update(Device device);

}

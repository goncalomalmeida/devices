package com.hardware.domain.api;

import com.hardware.domain.catalog.Device;
import com.hardware.domain.catalog.Page;

import java.util.List;

public interface ListDevicesUseCase {

    /**
     * Lists all devices in a paginated way and optionally filtering by 'brand'
     *
     * @param page  - pagination settings
     * @param brand - nullable param for filtering the list of devices
     * @return - a list of {@link Device} that is not nullable but can be empty
     */
    List<Device> findAll(Page page, String brand);
}

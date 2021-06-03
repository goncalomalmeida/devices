package com.hardware.domain.api;

import com.hardware.domain.catalog.Device;
import com.hardware.domain.catalog.Page;

import java.util.List;

public interface ListDevicesUseCase {

    // TODO add javadoc
    List<Device> findAll(Page page, String brand);
}

package com.hardware.persistence.impl;


import com.hardware.domain.catalog.Device;
import com.hardware.persistence.api.DeviceDAO;
import com.hardware.persistence.configuration.BaseTestIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;


public class DeviceDAOImplTest extends BaseTestIT {

    @Autowired
    private DeviceDAO subject;

    @Test
    public void create_whenAllFieldsAreNotNull_persistsAndReturns() {

        // given

        // when
        subject.create(new Device(null,
                                  "Name",
                                  "Brand",
                                  Instant.now()));

        // then
    }
}

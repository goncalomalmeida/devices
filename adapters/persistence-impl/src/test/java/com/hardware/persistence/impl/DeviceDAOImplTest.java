package com.hardware.persistence.impl;


import com.hardware.domain.catalog.Device;
import com.hardware.persistence.api.DeviceDAO;
import com.hardware.persistence.configuration.BaseTestIT;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class DeviceDAOImplTest extends BaseTestIT {

    @Autowired
    private DeviceDAO subject;

    @Test
    public void create_whenAllFieldsAreNotNull_persistsAndReturns() {

        // given
        final Device device = new Device(1L,
                                         "Name",
                                         "Brand",
                                         Instant.now());

        // when
        final Device result = subject.create(device);

        // then
        assertThat(result)
                .isNotNull()
                .isEqualTo(device);
    }

    @Test
    public void create_whenBrandIsNull_throwsException() {

        // given
        final Device device = new Device(2L,
                                         "Name",
                                         null,
                                         Instant.now());

        // when
        final ThrowableAssert.ThrowingCallable throwingCallable = () -> subject.create(device);

        // then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(DataIntegrityViolationException.class);

    }
}

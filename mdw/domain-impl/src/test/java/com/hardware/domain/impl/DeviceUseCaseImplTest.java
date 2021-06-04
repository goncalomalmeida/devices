package com.hardware.domain.impl;


import com.hardware.domain.catalog.Device;
import com.hardware.domain.catalog.DeviceRequest;
import com.hardware.domain.catalog.UpdateOperationOutcome;
import com.hardware.domain.impl.factories.DeviceFactory;
import com.hardware.persistence.api.DeviceDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeviceUseCaseImplTest {

    @Mock
    private DeviceDAO deviceDAO;

    @Mock
    private DeviceFactory deviceFactory;

    @InjectMocks
    private DeviceUseCaseImpl subject;

    @Test
    public void upsert_whenDeviceDoesNotExist_returnsCreatedOutcome() {

        // given
        long id = 1L;
        DeviceRequest deviceRequest = new DeviceRequest("name", "brand");
        doReturn(Optional.empty())
                .when(deviceDAO)
                .findById(eq(id));

        // when
        final UpdateOperationOutcome result = subject.upsert(id, deviceRequest);

        // then
        assertThat(result)
                .isEqualTo(UpdateOperationOutcome.CREATED);

        verify(deviceDAO).create(any());
    }

    @Test
    public void upsert_whenDeviceAlreadyExists_returnsUpdatedOutcome() {

        // given
        long id = 1L;
        DeviceRequest deviceRequest = new DeviceRequest("name", "brand");
        Device device = new Device(1L, "name", "brand", Instant.now());
        doReturn(Optional.of(device))
                .when(deviceDAO)
                .findById(eq(id));

        // when
        final UpdateOperationOutcome result = subject.upsert(id, deviceRequest);

        // then
        assertThat(result)
                .isEqualTo(UpdateOperationOutcome.UPDATED);

        verify(deviceDAO).update(any());
    }

}

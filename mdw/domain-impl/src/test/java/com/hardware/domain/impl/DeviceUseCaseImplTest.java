package com.hardware.domain.impl;


import com.hardware.persistence.api.DeviceDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeviceUseCaseImplTest {

    @Mock
    private DeviceDAO deviceDAO;

    @InjectMocks
    private DeviceUseCaseImpl subject;

    @Test
    public void create_whenNameIsEmpty_throwsValidationException() {

        // TODO
        // given

        // when

        // then
    }

}

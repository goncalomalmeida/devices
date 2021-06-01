package com.hardware.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hardware.domain.api.CreateDeviceUseCase;
import com.hardware.web.converters.DeviceResponseConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CreateDeviceUseCase createDeviceUseCase;

    @SpyBean
    private DeviceResponseConverter deviceResponseConverter;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void create_whenBodyIsWrong_returnsBadRequest() throws Exception {

        // given

        // when

        // then
    }

}

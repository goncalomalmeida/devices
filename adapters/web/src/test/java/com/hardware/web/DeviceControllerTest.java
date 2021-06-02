package com.hardware.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hardware.domain.api.CreateDeviceUseCase;
import com.hardware.domain.catalog.Device;
import com.hardware.web.converters.DeviceCreationRequestConverter;
import com.hardware.web.converters.DeviceResponseConverter;
import com.hardware.web.dtos.DeviceRequest;
import com.hardware.web.dtos.DeviceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CreateDeviceUseCase createDeviceUseCase;

    @SpyBean
    private DeviceResponseConverter deviceResponseConverter;

    @SpyBean
    private DeviceCreationRequestConverter deviceCreationRequestConverter;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void create_whenBodyIsWrong_returnsBadRequest() throws Exception {

        // given
        String wrongBody = "//";

        // when
        final ResultActions post = mvc.perform(
                post("/v1/devices")
                        .content(wrongBody)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        post.andExpect(status().isBadRequest());
    }

    @Test
    public void create_whenBodyIsCorrect_returnsResponse() throws Exception {

        // given
        DeviceRequest requestBody = new DeviceRequest("personal laptop", "samsung");
        Device expectedDevice = new Device(5L, requestBody.getName(), requestBody.getBrand(), Instant.now());

        doReturn(expectedDevice)
                .when(createDeviceUseCase)
                .create(any());

        // when
        final ResultActions post = mvc.perform(
                post("/v1/devices")
                        .content(objectMapper.writeValueAsString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        final String contentAsString = post.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        final DeviceResponse deviceResponse = objectMapper.readValue(contentAsString, DeviceResponse.class);

        assertThat(deviceResponse).isNotNull();
        assertThat(deviceResponse.getName()).isEqualTo(expectedDevice.getName());
        assertThat(deviceResponse.getBrand()).isEqualTo(expectedDevice.getBrand());
    }

}

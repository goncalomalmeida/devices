package com.hardware.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hardware.domain.api.CreateDeviceUseCase;
import com.hardware.domain.api.DeleteDeviceUseCase;
import com.hardware.domain.api.GetDeviceUseCase;
import com.hardware.domain.api.ListDevicesUseCase;
import com.hardware.domain.api.UpdateDeviceUseCase;
import com.hardware.domain.catalog.Device;
import com.hardware.domain.catalog.UpdateOperationOutcome;
import com.hardware.domain.catalog.exceptions.NotFoundException;
import com.hardware.web.converters.DeviceRequestConverter;
import com.hardware.web.converters.DeviceResponseConverter;
import com.hardware.web.converters.DeviceUpdateConverter;
import com.hardware.web.dtos.DeviceRequestDTO;
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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CreateDeviceUseCase createDeviceUseCase;

    @MockBean
    private GetDeviceUseCase getDeviceUseCase;

    @MockBean
    private ListDevicesUseCase listDevicesUseCase;

    @MockBean
    private UpdateDeviceUseCase updateDeviceUseCase;

    @MockBean
    private DeleteDeviceUseCase deleteDeviceUseCase;

    @SpyBean
    private DeviceResponseConverter deviceResponseConverter;

    @SpyBean
    private DeviceRequestConverter deviceRequestConverter;

    @SpyBean
    private DeviceUpdateConverter deviceUpdateConverter;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void create_whenBodyIsInvalid_returnsBadRequest() throws Exception {

        // given
        String invalidBody = "//";

        // when
        final ResultActions post = mvc.perform(
                post("/v1/devices")
                        .content(invalidBody)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        post.andExpect(status().isBadRequest());
    }

    @Test
    public void create_whenBodyIsCorrect_returnsResponse() throws Exception {

        // given
        DeviceRequestDTO requestBody = new DeviceRequestDTO("personal laptop", "samsung");
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
        final String contentAsString = post.andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        final DeviceResponse deviceResponse = objectMapper.readValue(contentAsString, DeviceResponse.class);

        assertThat(deviceResponse).isNotNull();
        assertThat(deviceResponse.getName()).isEqualTo(expectedDevice.getName());
        assertThat(deviceResponse.getBrand()).isEqualTo(expectedDevice.getBrand());
    }

    @Test
    public void findById_whenIdIsNotNumber_returnsBadRequest() throws Exception {

        // given
        String deviceId = "asdasd";

        // when
        final ResultActions get = mvc.perform(
                get("/v1/devices/{id}", deviceId)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        get.andExpect(status().isBadRequest());
    }

    @Test
    public void findById_whenDeviceNotFound_returnsNotFound() throws Exception {

        // given
        String deviceId = "1";

        doReturn(Optional.empty())
                .when(getDeviceUseCase)
                .findById(anyLong());

        // when
        final ResultActions get = mvc.perform(
                get("/v1/devices/{id}", deviceId)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        get.andExpect(status().isNotFound());
    }

    @Test
    public void findById_whenDeviceExists_returnsResponse() throws Exception {

        // given
        DeviceRequestDTO requestBody = new DeviceRequestDTO("personal laptop", "samsung");
        Device expectedDevice = new Device(5L, requestBody.getName(), requestBody.getBrand(), Instant.now());

        doReturn(Optional.of(expectedDevice))
                .when(getDeviceUseCase)
                .findById(anyLong());

        // when
        final ResultActions get = mvc.perform(
                get("/v1/devices/{id}", expectedDevice.getId())
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        final String contentAsString = get.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        final DeviceResponse deviceResponse = objectMapper.readValue(contentAsString, DeviceResponse.class);

        assertThat(deviceResponse).isNotNull().isEqualToComparingFieldByField(expectedDevice);
    }

    @Test
    public void findAll_whenEmptyDataSet_returnsEmptyList() throws Exception {

        // given
        doReturn(List.of())
                .when(listDevicesUseCase)
                .findAll(any(), any());

        // when
        final ResultActions get = mvc.perform(get("/v1/devices").contentType(MediaType.APPLICATION_JSON));

        // then
        final String contentAsString = get.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final List<DeviceResponse> result = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });

        assertThat(result).isEmpty();
    }

    @Test
    public void findAll_whenSizeIsTooHigh_returnsBadRequest() throws Exception {

        // given
        int size = Integer.MAX_VALUE;

        // when
        final ResultActions get = mvc.perform(get("/v1/devices")
                                                      .param("size", String.valueOf(size))
                                                      .contentType(MediaType.APPLICATION_JSON));

        // then
        get.andExpect(status().isBadRequest());
    }

    @Test
    public void findAll_whenSizeIsTooLow_returnsBadRequest() throws Exception {

        // given
        int size = -Integer.MAX_VALUE;

        // when
        final ResultActions get = mvc.perform(get("/v1/devices")
                                                      .param("size", String.valueOf(size))
                                                      .contentType(MediaType.APPLICATION_JSON));

        // then
        get.andExpect(status().isBadRequest());
    }

    @Test
    public void findAll_whenThereAreDevices_returnsList() throws Exception {

        // given
        final Device deviceOne = new Device(1L, "name1", "brand1", Instant.now());
        final Device deviceTwo = new Device(2L, "name2", "brand2", Instant.now());

        doReturn(List.of(deviceOne, deviceTwo))
                .when(listDevicesUseCase)
                .findAll(any(), any());

        // when
        final ResultActions get = mvc.perform(get("/v1/devices").contentType(MediaType.APPLICATION_JSON));

        // then
        final String contentAsString = get.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final List<DeviceResponse> result = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });

        assertThat(result)
                .isNotEmpty()
                .hasSize(2)
                .extracting(DeviceResponse::getName)
                .containsExactly("name1", "name2");
    }

    @Test
    public void upsert_whenBodyIsInvalid_returnsBadRequest() throws Exception {

        // given
        long deviceId = 1L;
        String invalidBody = "...";

        // when
        final ResultActions put = mvc.perform(put("/v1/devices/{id}", deviceId)
                                                      .content(invalidBody)
                                                      .contentType(MediaType.APPLICATION_JSON));

        // then
        put.andExpect(status().isBadRequest());
    }

    @Test
    public void upsert_whenEntityIsUpdated_returnsNoContent() throws Exception {

        // given
        long deviceId = 1L;
        DeviceRequestDTO requestBody = new DeviceRequestDTO("personal laptop", "samsung");

        doReturn(UpdateOperationOutcome.UPDATED)
                .when(updateDeviceUseCase)
                .upsert(anyLong(), any());

        // when
        final ResultActions put = mvc.perform(put("/v1/devices/{id}", deviceId)
                                                      .content(objectMapper.writeValueAsString(requestBody))
                                                      .contentType(MediaType.APPLICATION_JSON));

        // then
        put.andExpect(status().isNoContent());
    }

    @Test
    public void upsert_whenEntityIsCreated_returnsCreated() throws Exception {

        // given
        long deviceId = 1L;
        DeviceRequestDTO requestBody = new DeviceRequestDTO("personal laptop", "samsung");

        doReturn(UpdateOperationOutcome.CREATED)
                .when(updateDeviceUseCase)
                .upsert(anyLong(), any());

        // when
        final ResultActions put = mvc.perform(put("/v1/devices/{id}", deviceId)
                                                      .content(objectMapper.writeValueAsString(requestBody))
                                                      .contentType(MediaType.APPLICATION_JSON));

        // then
        put.andExpect(status().isCreated());
    }

    @Test
    public void partialUpdated_whenNotFound_returnsNotFound() throws Exception {

        // given
        long deviceId = 1L;

        doThrow(new NotFoundException(""))
                .when(updateDeviceUseCase)
                .partialUpdate(anyLong(), any());

        // when
        final ResultActions get = mvc.perform(patch("/v1/devices/{id}", deviceId)
                                                      .contentType(MediaType.APPLICATION_JSON));

        // then
        get.andExpect(status().isNotFound());
    }

    @Test
    public void partialUpdated_whenValid_returnsOk() throws Exception {

        // given
        long deviceId = 1L;
        Device expectedDevice = new Device(5L, "name", "brand", Instant.now());

        doReturn(expectedDevice)
                .when(updateDeviceUseCase)
                .partialUpdate(anyLong(), any());

        // when
        final ResultActions get = mvc.perform(patch("/v1/devices/{id}", deviceId)
                                                      .contentType(MediaType.APPLICATION_JSON));

        // then
        final String contentAsString = get.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final DeviceResponse result = objectMapper.readValue(contentAsString, DeviceResponse.class);

        assertThat(result).isEqualToComparingFieldByField(expectedDevice);
    }

    @Test
    public void delete_whenNotFound_returnsNotFound() throws Exception {

        // given
        long deviceId = 1L;

        doThrow(new NotFoundException(""))
                .when(deleteDeviceUseCase)
                .delete(eq(deviceId));

        // when
        final ResultActions get = mvc.perform(delete("/v1/devices/{id}", deviceId)
                                                      .contentType(MediaType.APPLICATION_JSON));

        // then
        get.andExpect(status().isNotFound());
    }

    @Test
    public void delete_whenFound_returnsNoContent() throws Exception {

        // given
        long deviceId = 1L;

        // when
        final ResultActions get = mvc.perform(delete("/v1/devices/{id}", deviceId)
                                                      .contentType(MediaType.APPLICATION_JSON));

        // then
        get.andExpect(status().isNoContent());
    }
}

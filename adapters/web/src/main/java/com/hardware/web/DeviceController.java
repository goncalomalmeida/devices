package com.hardware.web;

import com.hardware.domain.api.CreateDeviceUseCase;
import com.hardware.domain.catalog.Device;
import com.hardware.web.converters.DeviceCreationRequestConverter;
import com.hardware.web.converters.DeviceResponseConverter;
import com.hardware.web.dtos.DeviceRequest;
import com.hardware.web.dtos.DeviceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("v1/devices")
@Validated
public class DeviceController {

    private final CreateDeviceUseCase createDeviceUseCase;

    private final DeviceResponseConverter deviceResponseConverter;

    private final DeviceCreationRequestConverter deviceCreationRequestConverter;

    @PostMapping
    public DeviceResponse create(@Valid @RequestBody DeviceRequest deviceRequest) {

        final Device device = createDeviceUseCase.create(deviceCreationRequestConverter.convert(deviceRequest));

        return deviceResponseConverter.convert(device);
    }
}

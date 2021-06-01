package com.hardware.web;

import com.hardware.domain.api.CreateDeviceUseCase;
import com.hardware.web.converters.DeviceResponseConverter;
import com.hardware.web.dtos.DeviceRequest;
import com.hardware.web.dtos.DeviceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@Validated
public class DeviceController {

    private final CreateDeviceUseCase createDeviceUseCase;

    private final DeviceResponseConverter deviceResponseConverter;

    @PostMapping
    public DeviceResponse create(@RequestBody DeviceRequest deviceRequest) {

        // TODO
        return null;
    }
}

package com.hardware.web.converters;

import com.hardware.commons.Converter;
import com.hardware.domain.catalog.DeviceCreationRequest;
import com.hardware.web.dtos.DeviceRequest;
import org.springframework.stereotype.Component;

@Component
public class DeviceCreationRequestConverter implements Converter<DeviceRequest, DeviceCreationRequest> {

    @Override
    public DeviceCreationRequest convert(DeviceRequest source) {

        return new DeviceCreationRequest(source.getName(), source.getBrand());
    }
}

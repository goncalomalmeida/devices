package com.hardware.web.converters;

import com.hardware.commons.Converter;
import com.hardware.domain.catalog.DeviceRequest;
import com.hardware.web.dtos.DeviceRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class DeviceRequestConverter implements Converter<DeviceRequestDTO, DeviceRequest> {

    @Override
    public DeviceRequest convert(DeviceRequestDTO source) {

        return new DeviceRequest(source.getName(), source.getBrand());
    }
}

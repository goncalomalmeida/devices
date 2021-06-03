package com.hardware.web.converters;

import com.hardware.commons.Converter;
import com.hardware.domain.catalog.DeviceRequest;
import com.hardware.web.dtos.DeviceUpdateDTO;
import org.springframework.stereotype.Component;

@Component
public class DeviceUpdateConverter implements Converter<DeviceUpdateDTO, DeviceRequest> {

    @Override
    public DeviceRequest convert(DeviceUpdateDTO source) {

        if (source != null) {
            return new DeviceRequest(source.getName(), source.getBrand());
        } else {
            return new DeviceRequest(null, null);
        }
    }
}

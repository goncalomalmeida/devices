package com.hardware.web.converters;

import com.hardware.commons.Converter;
import com.hardware.domain.catalog.Device;
import com.hardware.web.dtos.DeviceResponse;
import org.springframework.stereotype.Component;

@Component
public class DeviceResponseConverter implements Converter<Device, DeviceResponse> {

    @Override
    public DeviceResponse convert(Device source) {

        return new DeviceResponse(source.getId(),
                                  source.getName(),
                                  source.getBrand(),
                                  source.getCreationTime());
    }
}

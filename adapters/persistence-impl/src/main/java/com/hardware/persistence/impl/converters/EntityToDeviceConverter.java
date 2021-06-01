package com.hardware.persistence.impl.converters;

import com.hardware.commons.Converter;
import com.hardware.domain.catalog.Device;
import com.hardware.persistence.impl.entities.DeviceEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityToDeviceConverter implements Converter<DeviceEntity, Device> {

    @Override
    public Device convert(DeviceEntity source) {

        return new Device(source.getId(),
                          source.getName(),
                          source.getBrand(),
                          source.getCreationTime());
    }
}

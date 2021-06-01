package com.hardware.persistence.impl.converters;

import com.hardware.commons.Converter;
import com.hardware.domain.catalog.Device;
import com.hardware.persistence.impl.entities.DeviceEntity;
import org.springframework.stereotype.Component;

@Component
public class DeviceToEntityConverter implements Converter<Device, DeviceEntity> {

    @Override
    public DeviceEntity convert(Device source) {

        final DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setName(source.getName());
        deviceEntity.setBrand(source.getBrand());
        deviceEntity.setCreationTime(source.getCreationTime());
        return deviceEntity;
    }
}

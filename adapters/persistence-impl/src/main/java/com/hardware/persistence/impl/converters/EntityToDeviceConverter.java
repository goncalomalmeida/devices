package com.hardware.persistence.impl.converters;

import com.hardware.commons.Converter;
import com.hardware.domain.catalog.Device;
import com.hardware.persistence.impl.entities.AbstractDeviceEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityToDeviceConverter implements Converter<AbstractDeviceEntity, Device> {

    @Override
    public Device convert(AbstractDeviceEntity source) {

        return new Device(source.getId(),
                          source.getName(),
                          source.getBrand(),
                          source.getCreationTime());
    }
}

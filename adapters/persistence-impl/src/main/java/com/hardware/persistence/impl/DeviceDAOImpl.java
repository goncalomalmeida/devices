package com.hardware.persistence.impl;

import com.hardware.domain.catalog.Device;
import com.hardware.persistence.api.DeviceDAO;
import com.hardware.persistence.impl.converters.DeviceToEntityConverter;
import com.hardware.persistence.impl.converters.EntityToDeviceConverter;
import com.hardware.persistence.impl.entities.DeviceEntity;
import com.hardware.persistence.impl.repositories.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeviceDAOImpl implements DeviceDAO {

    private final DeviceRepository deviceRepository;

    private final DeviceToEntityConverter deviceToEntityConverter;

    private final EntityToDeviceConverter entityToDeviceConverter;

    @Override
    public Device create(Device device) {

        final DeviceEntity savedEntity = deviceRepository.save(deviceToEntityConverter.convert(device));
        return entityToDeviceConverter.convert(savedEntity);
    }

}

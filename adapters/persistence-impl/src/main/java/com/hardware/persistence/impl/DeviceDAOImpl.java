package com.hardware.persistence.impl;

import com.hardware.domain.catalog.Device;
import com.hardware.domain.catalog.Page;
import com.hardware.domain.catalog.exceptions.NotFoundException;
import com.hardware.persistence.api.DeviceDAO;
import com.hardware.persistence.impl.converters.DeviceToEntityConverter;
import com.hardware.persistence.impl.converters.EntityToDeviceConverter;
import com.hardware.persistence.impl.entities.DeviceEntity;
import com.hardware.persistence.impl.repositories.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Device> findById(long id) {

        return deviceRepository.findById(id)
                .map(entityToDeviceConverter::convert);
    }

    @Override
    public List<Device> findAll(Page page) {

        return deviceRepository.findAll(PageRequest.of(page.getPageNumber(), page.getSize()))
                .map(entityToDeviceConverter::convert)
                .toList();
    }

    @Override
    public void delete(long id) {

        try {
            deviceRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Device not found");
        }
    }

}

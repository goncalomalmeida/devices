package com.hardware.persistence.impl.repositories;

import com.hardware.persistence.impl.entities.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    // FIXME
    DeviceEntity findByName(String productName);
}

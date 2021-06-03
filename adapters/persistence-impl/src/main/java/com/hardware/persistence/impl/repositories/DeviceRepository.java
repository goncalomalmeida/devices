package com.hardware.persistence.impl.repositories;

import com.hardware.persistence.impl.entities.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long>, JpaSpecificationExecutor<DeviceEntity> {

    // FIXME
    DeviceEntity findByName(String productName);
}

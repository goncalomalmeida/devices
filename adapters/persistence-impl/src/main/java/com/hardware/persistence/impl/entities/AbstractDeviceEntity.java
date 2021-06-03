package com.hardware.persistence.impl.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractDeviceEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "creation_time", nullable = false)
    private Instant creationTime;

    public abstract long getId();
}

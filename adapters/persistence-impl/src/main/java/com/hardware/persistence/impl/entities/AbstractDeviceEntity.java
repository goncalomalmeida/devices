package com.hardware.persistence.impl.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractDeviceEntity {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "brand", nullable = false)
    private String brand;

    @NotNull
    @Column(name = "creation_time", nullable = false, updatable = false)
    private Instant creationTime;

    public abstract long getId();
}

package com.hardware.domain.catalog;

import lombok.Value;

import java.time.Instant;

@Value
public class Device {

    Long id;

    String name;

    String brand;

    Instant creationTime;

}

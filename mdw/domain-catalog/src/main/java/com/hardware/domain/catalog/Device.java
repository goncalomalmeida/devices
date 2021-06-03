package com.hardware.domain.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@AllArgsConstructor
@Value
@Builder(builderClassName = "Builder", toBuilder = true)
public class Device {

    Long id;

    String name;

    String brand;

    Instant creationTime;

}

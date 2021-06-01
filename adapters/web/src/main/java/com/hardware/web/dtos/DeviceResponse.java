package com.hardware.web.dtos;

import lombok.Value;

import java.time.Instant;

@Value
public class DeviceResponse {

    long id;

    String name;

    String brand;

    Instant creationTime;
}

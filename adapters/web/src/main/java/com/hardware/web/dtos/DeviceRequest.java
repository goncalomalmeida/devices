package com.hardware.web.dtos;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@RequiredArgsConstructor
@Builder(builderClassName = "Builder")
public class DeviceRequest {

    @NotBlank
    String name;

    @NotBlank
    String brand;

}

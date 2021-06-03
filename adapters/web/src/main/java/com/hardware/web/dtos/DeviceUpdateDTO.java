package com.hardware.web.dtos;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
@Builder(builderClassName = "Builder")
public class DeviceUpdateDTO {

    String name;

    String brand;

}

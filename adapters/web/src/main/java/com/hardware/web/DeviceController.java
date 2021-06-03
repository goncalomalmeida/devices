package com.hardware.web;

import com.hardware.domain.api.CreateDeviceUseCase;
import com.hardware.domain.api.DeleteDeviceUseCase;
import com.hardware.domain.api.GetDeviceUseCase;
import com.hardware.domain.api.ListDevicesUseCase;
import com.hardware.domain.api.UpdateDeviceUseCase;
import com.hardware.domain.catalog.Device;
import com.hardware.domain.catalog.Page;
import com.hardware.domain.catalog.UpdateOperationOutcome;
import com.hardware.domain.catalog.exceptions.NotFoundException;
import com.hardware.web.converters.DeviceRequestConverter;
import com.hardware.web.converters.DeviceResponseConverter;
import com.hardware.web.dtos.DeviceRequestDTO;
import com.hardware.web.dtos.DeviceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("v1/devices")
@Validated
public class DeviceController {

    private final CreateDeviceUseCase createDeviceUseCase;

    private final GetDeviceUseCase getDeviceUseCase;

    private final ListDevicesUseCase listDevicesUseCase;

    private final UpdateDeviceUseCase updateDeviceUseCase;

    private final DeleteDeviceUseCase deleteDeviceUseCase;

    private final DeviceResponseConverter deviceResponseConverter;

    private final DeviceRequestConverter deviceRequestConverter;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DeviceResponse create(@Valid @RequestBody DeviceRequestDTO deviceRequestDTO) {

        final Device device = createDeviceUseCase.create(deviceRequestConverter.convert(deviceRequestDTO));

        return deviceResponseConverter.convert(device);
    }

    @GetMapping("{id}")
    public DeviceResponse findById(@PathVariable long id) {

        return getDeviceUseCase.findById(id)
                .map(deviceResponseConverter::convert)
                .orElseThrow(NotFoundException::deviceNotFoundException);
    }

    @GetMapping
    public List<DeviceResponse> findAll(@RequestParam(name = "pageNumber", defaultValue = "0")
                                        @Min(value = 0) Integer pageNumber,
                                        @RequestParam(name = "size", defaultValue = "10")
                                        @Max(value = 1000)
                                        @Min(value = 0) Integer size,
                                        @RequestParam(name = "brand", required = false) String brand) {

        return listDevicesUseCase.findAll(new Page(pageNumber, size), brand)
                .stream()
                .map(deviceResponseConverter::convert)
                .collect(Collectors.toList());
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(
            @PathVariable long id,
            @Valid @RequestBody DeviceRequestDTO deviceRequestDTO) {

        UpdateOperationOutcome updateOperationOutcome = updateDeviceUseCase.update(id, deviceRequestConverter.convert(deviceRequestDTO));

        HttpStatus httpStatus = UpdateOperationOutcome.CREATED.equals(updateOperationOutcome) ? HttpStatus.CREATED : HttpStatus.NO_CONTENT;

        return new ResponseEntity<>(httpStatus);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {

        deleteDeviceUseCase.delete(id);
    }

}

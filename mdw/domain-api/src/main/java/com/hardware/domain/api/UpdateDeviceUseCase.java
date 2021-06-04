package com.hardware.domain.api;

import com.hardware.domain.catalog.Device;
import com.hardware.domain.catalog.DeviceRequest;
import com.hardware.domain.catalog.UpdateOperationOutcome;
import com.hardware.domain.catalog.exceptions.NotFoundException;

public interface UpdateDeviceUseCase {

    /**
     * Updates or creates a complete {@link Device} using the incoming data.
     * If the {@link Device} 'id' does not exist then it is created, thus making this operation idempotent.
     *
     * @param id            - the {@link Device} identifier
     * @param deviceRequest - relevant data for creating/fully updating a {@link Device}
     * @return indicates if the operation was indeed an update or a creation
     */
    UpdateOperationOutcome upsert(long id, DeviceRequest deviceRequest);

    /**
     * Updates a {@link Device} using the incoming data if any. Only non-null fields will be considered for updating the entity.
     * It throws a {@link NotFoundException} if the 'id' is not found.
     *
     * @param id            - the {@link Device} identifier
     * @param deviceRequest - relevant data for updating a {@link Device}. All fields are optional so it can happen that the entity isn't
     *                      touched at all.
     * @return the updated {@link Device}
     */
    Device partialUpdate(long id, DeviceRequest deviceRequest);
}

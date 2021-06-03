package com.hardware.web.paging;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public interface Pageable {

    @Min(value = 0)
    Integer getPageNumber();

    @Max(value = 1000)
    @Min(value = 0)
    Integer getSize();
}

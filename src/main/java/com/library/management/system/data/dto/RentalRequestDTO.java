package com.library.management.system.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RentalRequestDTO {
    private Long userId;
    private Long bookId;
}

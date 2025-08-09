package com.library.management.system.data.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RentalResponseDTO {
    private Long id;
    private Long profileId;
    private Long bookId;
    private LocalDateTime rentedAt;
    private LocalDateTime returnedAt;
}

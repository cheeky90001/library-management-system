package com.library.management.system.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDTO {
    private String name;
    private String author;
}

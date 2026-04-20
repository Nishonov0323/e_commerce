package com.workshop.zarif.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryCreateRequest {
    @NotBlank(message = "Kategoriya nomi bo'sh bo'lmasligi kerak")
    private String name;

    private String description;

    // Agar ota-bolasi kerak bo'lsa
    private Long parentId;
}


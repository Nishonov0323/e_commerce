package com.workshop.zarif.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductCreateRequest {
    @NotBlank(message = "Mahsulot nomi bo'sh bo'lmasligi kerak")
    private String name;

    private String description;

    @NotNull(message = "Narx kiritilishi shart")
    @Positive(message = "Narx 0 dan katta bo'lishi kerak")
    private BigDecimal price;

    @NotNull(message = "Soni kiritilishi shart")
    @Positive(message = "Soni 0 dan katta bo'lishi kerak")
    private Integer stockQuantity;

    @NotNull(message = "Category ID kiritilishi shart")
    @Positive(message = "Category ID musbat bo'lishi kerak")
    private Long categoryId;

    @NotNull(message = "Vendor ID kiritilishi shart")
    @Positive(message = "Vendor ID musbat bo'lishi kerak")
    private Long vendorId;

    private List<String> imageUrls;
}

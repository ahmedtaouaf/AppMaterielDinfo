package com.app.materiel.Dto;

import lombok.Data;

@Data
public class TypeSummaryDto {
    private String type;
    private Long quantity;
    private Long disponibleCount;
    private Long indisponibleCount;

    public TypeSummaryDto(String type, Long quantity, Long disponibleCount, Long indisponibleCount) {
        this.type = type;
        this.quantity = quantity;
        this.disponibleCount = disponibleCount;
        this.indisponibleCount = indisponibleCount;
    }

}


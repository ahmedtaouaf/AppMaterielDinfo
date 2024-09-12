package com.app.materiel.Dto;

import lombok.Data;

@Data
public class StockDto {
    private String designation;
    private String nserie;
    private String observation;
    private Long typeId;
    private boolean withSerialNumber;
    private int quantity;
}


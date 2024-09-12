package com.app.materiel;

import lombok.Data;

@Data
public class StockDto {
    private String designation;
    private String nserie;
    private String observation;
    private Long typeId;
    private Long statusId;
    private boolean withSerialNumber;
    private int quantity;
}


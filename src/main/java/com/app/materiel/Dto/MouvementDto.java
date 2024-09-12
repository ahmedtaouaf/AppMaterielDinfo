package com.app.materiel.Dto;

import lombok.Data;

@Data
public class MouvementDto {

    private Long stockId;
    private Long statusId;
    private Long positionId;
    private String observation;
}


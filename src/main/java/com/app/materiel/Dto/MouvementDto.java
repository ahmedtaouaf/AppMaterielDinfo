package com.app.materiel.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class MouvementDto {

    private Long stockId;
    private Long statusId;
    private Long positionId;
    private Long responsableId;
    private String observation;
    private String datee;
    private String dateentre;
}


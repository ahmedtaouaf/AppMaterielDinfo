package com.app.materiel.Service;

import com.app.materiel.Dto.MouvementDto;
import com.app.materiel.Entity.Mouvement;
import com.app.materiel.Entity.Position;
import com.app.materiel.Entity.Status;
import com.app.materiel.Entity.Stock;
import com.app.materiel.Repository.MouvementRepository;
import com.app.materiel.Repository.PositionRepository;
import com.app.materiel.Repository.StatusRepository;
import com.app.materiel.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MouvementService {

    @Autowired
    private MouvementRepository mouvementRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private PositionRepository positionRepository;

    public void saveMouvement(MouvementDto mouvementDto) {
        // Retrieve the stock, position, and status entities
        Stock stock = stockRepository.findById(mouvementDto.getStockId()).orElseThrow();
        Status status = statusRepository.findById(mouvementDto.getStatusId()).orElseThrow();
        Position position = positionRepository.findById(mouvementDto.getPositionId()).orElseThrow();

        // Create and save the Mouvement
        Mouvement mouvement = new Mouvement();
        mouvement.setDatee(new Date());
        mouvement.setObservation(mouvementDto.getObservation());
        mouvement.setStock(stock);
        mouvement.setStatus(status);
        mouvement.setPosition(position);
        mouvementRepository.save(mouvement);

        // Update the stock status to "Indisponible"
        stock.setStatus(statusRepository.findByLibelle("INDISPONIBLE"));
        stockRepository.save(stock);
    }
}


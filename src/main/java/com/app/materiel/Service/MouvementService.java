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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        Stock stock = stockRepository.findById(mouvementDto.getStockId()).orElseThrow();
        Status status = statusRepository.findById(mouvementDto.getStatusId()).orElseThrow();
        Position position = positionRepository.findById(mouvementDto.getPositionId()).orElseThrow();


        Mouvement mouvement = new Mouvement();
        mouvement.setDatee(new Date());
        mouvement.setObservation(mouvementDto.getObservation());
        mouvement.setStock(stock);
        mouvement.setStatus(status);
        mouvement.setPosition(position);
        mouvementRepository.save(mouvement);


        stock.setStatus(statusRepository.findByLibelle("INDISPONIBLE"));
        stockRepository.save(stock);
    }

    public Page<Mouvement> findAllmouvements(String searchTerm, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        if (searchTerm == null || searchTerm.isEmpty()) {
            return mouvementRepository.findAllmvns(pageable);
        }
        return mouvementRepository.findAllBySearchTerm(searchTerm, pageable);
    }


    public Page<Mouvement> findAllmouvementsEntree(String searchTerm, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        if (searchTerm == null || searchTerm.isEmpty()) {
            return mouvementRepository.findAllmvnsEntree(pageable);
        }
        return mouvementRepository.findAllBySearchTermEntree(searchTerm, pageable);
    }


    public void saveEntreeMouvement(MouvementDto mouvementDto) {

        Stock stock = stockRepository.findById(mouvementDto.getStockId()).orElseThrow();
        Mouvement lastMouvement = mouvementRepository.findTopByStockOrderByDateeDesc(stock);
        Status disponibleStatus = statusRepository.findByLibelle("ENTREE AU STOCK");


        Mouvement mouvement = new Mouvement();

        mouvement.setDateentree(new Date());
        mouvement.setDatee(lastMouvement.getDatee());
        mouvement.setObservation(mouvementDto.getObservation());
        mouvement.setStock(stock);
        mouvement.setStatus(disponibleStatus);
        mouvement.setPosition(lastMouvement.getPosition());

        mouvementRepository.save(mouvement);

        stock.setStatus(disponibleStatus);
        stockRepository.save(stock);
    }



}


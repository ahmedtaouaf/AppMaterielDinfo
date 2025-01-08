package com.app.materiel.Service;

import com.app.materiel.Dto.MouvementDto;
import com.app.materiel.Entity.*;
import com.app.materiel.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private ResponsableRepository responsableRepository;

    public void saveMouvement(MouvementDto mouvementDto) {

        Stock stock = stockRepository.findById(mouvementDto.getStockId()).orElseThrow();
        Status status = statusRepository.findById(mouvementDto.getStatusId()).orElseThrow();
        Position position = positionRepository.findById(mouvementDto.getPositionId()).orElseThrow();
        Responsable responsable = responsableRepository.findById(mouvementDto.getResponsableId()).orElseThrow();

        Mouvement mouvement = new Mouvement();


        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            mouvement.setDatee(formatter.parse(mouvementDto.getDatee()));
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format for datee", e);
        }

        mouvement.setObservation(mouvementDto.getObservation());
        mouvement.setStock(stock);
        mouvement.setStatus(status);
        mouvement.setPosition(position);
        mouvement.setResponsable(responsable);
        mouvementRepository.save(mouvement);

        stock.setStatus(statusRepository.findByLibelle("INDISPONIBLE"));
        stockRepository.save(stock);
    }

    public Page<Mouvement> findAllmouvements(String searchTerm, int page) {
        Pageable pageable = PageRequest.of(page, 25);
        if (searchTerm == null || searchTerm.isEmpty()) {
            return mouvementRepository.findAllmvns(pageable);
        }
        return mouvementRepository.findAllBySearchTerm(searchTerm, pageable);
    }


    public Page<Mouvement> findAllmouvementsEntree(String searchTerm, int page) {
        Pageable pageable = PageRequest.of(page, 25);
        if (searchTerm == null || searchTerm.isEmpty()) {
            return mouvementRepository.findAllmvnsEntree(pageable);
        }
        return mouvementRepository.findAllBySearchTermEntree(searchTerm, pageable);
    }


    public void saveEntreeMouvement(MouvementDto mouvementDto) {

        Stock stock = stockRepository.findById(mouvementDto.getStockId()).orElseThrow();
        Mouvement lastMouvement = mouvementRepository.findTopByStockOrderByDateeDesc(stock);
        Status disponibleStatus = statusRepository.findByLibelle("DISPONIBLE");


        Mouvement mouvement = new Mouvement();

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            mouvement.setDateentree(formatter.parse(mouvementDto.getDateentre()));
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format for datee", e);
        }
        mouvement.setDatee(lastMouvement.getDatee());
        mouvement.setObservation(mouvementDto.getObservation());
        mouvement.setStock(stock);
        mouvement.setStatus(disponibleStatus);
        mouvement.setPosition(lastMouvement.getPosition());
        mouvement.setResponsable(lastMouvement.getResponsable());

        mouvementRepository.save(mouvement);

        stock.setStatus(disponibleStatus);
        stockRepository.save(stock);
    }

    public Page<Mouvement> findMovementsByStockOrderByDateDesc(Stock stock, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return mouvementRepository.findByStockOrderByDateeDescDateentreeDesc(stock, pageable);
    }

    public List<Mouvement> findLastSixMouvements() {
        return mouvementRepository.findTop6ByOrderByDateeDesc();
    }

    public Integer totalMvn() {

        return mouvementRepository.totalMvn();
    }

    public List<Mouvement> findMouvementsByDateRange(Date startDate, Date endDate) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date adjustedEndDate = calendar.getTime();

        return mouvementRepository.findByDateeBetweenOrDateentreeBetween(startDate, adjustedEndDate, startDate, adjustedEndDate);
    }



}


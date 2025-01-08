package com.app.materiel.Service;

import com.app.materiel.Dto.TypeSummaryDto;
import com.app.materiel.Entity.Status;
import com.app.materiel.Entity.Stock;
import com.app.materiel.Entity.Type;
import com.app.materiel.Repository.StatusRepository;
import com.app.materiel.Repository.StockRepository;
import com.app.materiel.Repository.TypeRepository;
import com.app.materiel.Dto.StockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private StatusRepository statusRepository;

    public void saveStock(StockDto stockDto) {

        Type type = typeRepository.findById(stockDto.getTypeId()).orElseThrow();

        Status status = statusRepository.findByLibelle("DISPONIBLE");

        if (stockDto.isWithSerialNumber()) {
            Stock stock = new Stock();

            stock.setDesignation(stockDto.getDesignation());
            stock.setNserie(stockDto.getNserie());
            stock.setObservation(stockDto.getObservation());
            stock.setDatee(new Date());
            stock.setType(type);
            stock.setStatus(status);
            stock.setCaracteristiques(stockDto.getCaracteristiques());
            stockRepository.save(stock);
        }

        else {

            for (int i = 0; i < stockDto.getQuantity(); i++) {
                Stock stock = new Stock();
                stock.setDesignation(stockDto.getDesignation());
                stock.setNserie("Sans");
                stock.setObservation(stockDto.getObservation());
                stock.setCaracteristiques(stockDto.getCaracteristiques());
                stock.setDatee(new Date());
                stock.setType(type);
                stock.setStatus(status);
                stockRepository.save(stock);

                stock.setNserie("SN_" + stock.getId());
                stockRepository.save(stock);
            }
        }
    }

    public Page<Stock> findAllStocks(String searchTerm, int page) {
        Pageable pageable = PageRequest.of(page, 35);
        if (searchTerm == null || searchTerm.isEmpty()) {
            return stockRepository.findAll(pageable);
        }
        return stockRepository.findAllBySearchTerm(searchTerm, pageable);
    }

    public List<TypeSummaryDto> getTypeSummary() {
        List<Stock> stocks = stockRepository.findAll();


        Map<String, TypeSummaryDto> summaryMap = new HashMap<>();

        for (Stock stock : stocks) {
            String type = stock.getType().getLibelle();

            summaryMap.putIfAbsent(type, new TypeSummaryDto(type, 0L, 0L, 0L));
            TypeSummaryDto summary = summaryMap.get(type);


            summary.setQuantity(summary.getQuantity() + 1);


            if (stock.getStatus().getLibelle().equalsIgnoreCase("DISPONIBLE")) {
                summary.setDisponibleCount(summary.getDisponibleCount() + 1);
            } else if (stock.getStatus().getLibelle().equalsIgnoreCase("INDISPONIBLE")) {
                summary.setIndisponibleCount(summary.getIndisponibleCount() + 1);
            }
        }

        return new ArrayList<>(summaryMap.values());
    }

    public Stock findStockById(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Stock not found for id: " + id));
    }

    public List<Stock> findStocksByTypeAndStatus(String type, String status) {
        return stockRepository.findByTypeLibelleAndStatusLibelle(type, status);
    }

    public List<Stock> findAllStocks() {
        return stockRepository.findAll();
    }

    public Integer totalStock() {

        return stockRepository.totalStock();
    }
    public boolean existsByNserie(String nserie) {
        return stockRepository.existsByNserie(nserie);
    }

    public Page<Stock> findStocksByType(Long typeId, String searchTerm, int page) {
        if (searchTerm != null && !searchTerm.isEmpty()) {
            return stockRepository.findByTypeIdAndDesignationContainingIgnoreCase(typeId, searchTerm, PageRequest.of(page, 35));
        }
        return stockRepository.findByTypeId(typeId, PageRequest.of(page, 35));
    }





}


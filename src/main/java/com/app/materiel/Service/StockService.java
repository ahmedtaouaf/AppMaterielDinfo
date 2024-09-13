package com.app.materiel.Service;

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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

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


        Status status = statusRepository.findById(1L).orElseThrow();

        if (stockDto.isWithSerialNumber()) {

            Stock stock = new Stock();
            stock.setDesignation(stockDto.getDesignation());
            stock.setNserie(stockDto.getNserie());
            stock.setObservation(stockDto.getObservation());
            stock.setDatee(new Date());
            stock.setType(type);
            stock.setStatus(status);
            stockRepository.save(stock);
        } else {

            for (int i = 0; i < stockDto.getQuantity(); i++) {
                Stock stock = new Stock();
                stock.setDesignation(stockDto.getDesignation());
                stock.setNserie("Sans");
                stock.setObservation(stockDto.getObservation());
                stock.setDatee(new Date());
                stock.setType(type);
                stock.setStatus(status);
                stockRepository.save(stock);
            }
        }
    }

    public Page<Stock> findAllStocks(String searchTerm, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        if (searchTerm == null || searchTerm.isEmpty()) {
            return stockRepository.findAll(pageable);
        }
        return stockRepository.findAllBySearchTerm(searchTerm, pageable);
    }
}


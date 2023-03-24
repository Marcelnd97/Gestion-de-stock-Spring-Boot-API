package com.damo.gestionDeStock.service;

import com.damo.gestionDeStock.dto.MouveStockDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MouveStkService {

    MouveStockDto save(MouveStockDto mouveStk);
    MouveStockDto findById(Integer id);
    List<MouveStockDto> findAll();
    void delete(Integer id);
}

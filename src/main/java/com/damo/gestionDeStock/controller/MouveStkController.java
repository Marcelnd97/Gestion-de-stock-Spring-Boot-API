package com.damo.gestionDeStock.controller;

import com.damo.gestionDeStock.controller.api.MouveStkApi;
import com.damo.gestionDeStock.dto.MouveStockDto;
import com.damo.gestionDeStock.service.MouveStkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MouveStkController implements MouveStkApi {


    private MouveStkService mouveStkService;

    @Autowired
    public MouveStkController(MouveStkService mouveStkService) {
        this.mouveStkService = mouveStkService;
    }

    @Override
    public MouveStockDto save(MouveStockDto mouveStk) {
        return mouveStkService.save(mouveStk);
    }

    @Override
    public MouveStockDto findById(Integer id) {
        return mouveStkService.findById(id);
    }

    @Override
    public List<MouveStockDto> findAll() {
        return mouveStkService.findAll();
    }

    @Override
    public void delete(Integer id) {
        mouveStkService.delete(id);
    }
}

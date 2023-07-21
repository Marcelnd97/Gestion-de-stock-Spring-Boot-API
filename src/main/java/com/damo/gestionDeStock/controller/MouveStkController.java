package com.damo.gestionDeStock.controller;

import com.damo.gestionDeStock.controller.api.MouveStkApi;
import com.damo.gestionDeStock.dto.MouveStockDto;
import com.damo.gestionDeStock.service.MouveStkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MouveStkController implements MouveStkApi {


    private MouveStkService mouveStkService;

    @Autowired
    public MouveStkController(MouveStkService mouveStkService) {
        this.mouveStkService = mouveStkService;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        return mouveStkService.stockReelArticle(idArticle);
    }

    @Override
    public List<MouveStockDto> mvtStkArticle(Integer idArticle) {
        return mouveStkService.mvtStkArticle(idArticle);
    }

    @Override
    public MouveStockDto entreeStock(MouveStockDto dto) {
        return mouveStkService.entreeStock(dto);
    }

    @Override
    public MouveStockDto sortieStock(MouveStockDto dto) {
        return mouveStkService.sortieStock(dto);
    }

    @Override
    public MouveStockDto correctionStockPos(MouveStockDto dto) {
        return mouveStkService.correctionStockPos(dto);
    }

    @Override
    public MouveStockDto correctionStockNeg(MouveStockDto dto) {
        return mouveStkService.correctionStockNeg(dto);
    }
    }

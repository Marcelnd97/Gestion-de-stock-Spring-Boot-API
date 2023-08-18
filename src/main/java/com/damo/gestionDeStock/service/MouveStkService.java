package com.damo.gestionDeStock.service;

import com.damo.gestionDeStock.dto.ArticleDto;
import com.damo.gestionDeStock.dto.MouveStockDto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MouveStkService {

    BigDecimal stockReelArticle(Integer idArticle);
    MouveStockDto entreeStock(MouveStockDto dto);
    MouveStockDto sortieStock(MouveStockDto dto);
    MouveStockDto correctionStockPos(MouveStockDto dto);
    MouveStockDto correctionStockNeg(MouveStockDto dto);
    List<MouveStockDto> mvtStkArticle(Integer idArticle);
    List<MouveStockDto> findAllMouveStock();
}

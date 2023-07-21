package com.damo.gestionDeStock.service.impl;

import com.damo.gestionDeStock.dto.MouveStockDto;
import com.damo.gestionDeStock.handlers.exception.EntityNotFoundException;
import com.damo.gestionDeStock.handlers.exception.ErrorCodes;
import com.damo.gestionDeStock.handlers.exception.InvalidEntityException;
import com.damo.gestionDeStock.model.MouveStock;
import com.damo.gestionDeStock.model.TypeMouveStk;
import com.damo.gestionDeStock.repository.MouveStkRepository;
import com.damo.gestionDeStock.repository.MvtStockRepository;
import com.damo.gestionDeStock.service.ArticleService;
import com.damo.gestionDeStock.service.MouveStkService;
import com.damo.gestionDeStock.validator.MouveStockValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MouveStkServiceImpl implements MouveStkService {


    private MvtStockRepository mvtStockRepository;
    private ArticleService articleService;

    @Autowired
    public MouveStkServiceImpl(MvtStockRepository mvtStockRepository, ArticleService articleService) {
        this.mvtStockRepository = mvtStockRepository;
        this.articleService = articleService;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        if (idArticle == null){
            log.warn("ID article is null");
            return BigDecimal.valueOf(-1);
        }
        articleService.findById(idArticle);
        return mvtStockRepository.stockReelArticle(idArticle);
    }

    @Override
    public MouveStockDto entreeStock(MouveStockDto dto) {
       return entreePositive(dto, TypeMouveStk.ENTREE);
    }

    @Override
    public MouveStockDto sortieStock(MouveStockDto dto) {
        return sortieNegative(dto, TypeMouveStk.SORTIE);
    }


    @Override
    public MouveStockDto correctionStockPos(MouveStockDto dto) {
        return sortieNegative(dto, TypeMouveStk.CORRECTION_POS);
    }

    @Override
    public MouveStockDto correctionStockNeg(MouveStockDto dto) {
        return sortieNegative(dto, TypeMouveStk.CORRECTION_NEG);
    }

    @Override
    public List<MouveStockDto> mvtStkArticle(Integer idArticle) {
        return mvtStockRepository.findAllByArticleId(idArticle).stream()
                .map(MouveStockDto::fromEntity)
                .collect(Collectors.toList());
    }

    private MouveStockDto entreePositive(MouveStockDto dto, TypeMouveStk typeMouveStk){
        List<String> errors = MouveStockValidator.validator(dto);
        if (!errors.isEmpty()) {
            log.error("Article is not valide {}", dto);
            throw new InvalidEntityException("Le mouvement du stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
        }
        dto.setQuantite(BigDecimal.valueOf(
                Math.abs(dto.getQuantite().doubleValue())
        ));
        dto.setTypeMouveStk(typeMouveStk);
        return MouveStockDto.fromEntity(
                mvtStockRepository.save(MouveStockDto.toEntity(dto))
        );
    }
    private MouveStockDto sortieNegative(MouveStockDto dto, TypeMouveStk typeMouveStk) {
        List<String> errors = MouveStockValidator.validator(dto);
        if (!errors.isEmpty()) {
            log.error("Article is not valide {}", dto);
            throw new InvalidEntityException("Le mouvement du stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
        }
        dto.setQuantite(BigDecimal.valueOf(
                Math.abs(dto.getQuantite().doubleValue() * -1)
        ));
        dto.setTypeMouveStk(typeMouveStk);
        return MouveStockDto.fromEntity(
                mvtStockRepository.save(MouveStockDto.toEntity(dto))
        );
    }
}

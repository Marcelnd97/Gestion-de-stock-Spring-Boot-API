package com.damo.gestionDeStock.dto;

import com.damo.gestionDeStock.model.MouveStock;
import com.damo.gestionDeStock.model.SourceMvt;
import com.damo.gestionDeStock.model.TypeMouveStk;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MouveStockDto {

    private Integer id;

    private Instant dateMouveStock;

    private BigDecimal quantite;

    private ArticleDto article;

    private TypeMouveStk typeMouveStk;

    private SourceMvt sourceMvt;
    private Integer idEntreprise;

    public static MouveStockDto fromEntity(MouveStock mouveStock) {
        if (mouveStock == null) {
            return null;
            // Todo throw an Exception
        }

        return MouveStockDto.builder()
                .id(mouveStock.getId())
                .quantite(mouveStock.getQuantite())
                .dateMouveStock(mouveStock.getDateMouveStock())
                .idEntreprise(mouveStock.getIdEntreprise())
                .article(ArticleDto.fromEntity(mouveStock.getArticle()))
                .typeMouveStk(mouveStock.getTypeMouveStk())
                .sourceMvt(mouveStock.getSourceMvt())
                .build();
    }

    public static MouveStock toEntity(MouveStockDto mouveStockDto) {
        if (mouveStockDto == null) {
            return null;
            // Todo throw an Exception
        }
        MouveStock mouveStock = new MouveStock();

        mouveStock.setId(mouveStockDto.getId());
        mouveStock.setQuantite(mouveStockDto.getQuantite());
        mouveStock.setDateMouveStock(mouveStockDto.getDateMouveStock());
        mouveStock.setIdEntreprise(mouveStockDto.getIdEntreprise());
        mouveStock.setSourceMvt(mouveStockDto.getSourceMvt());
        mouveStock.setArticle(ArticleDto.toEntity(mouveStockDto.getArticle()));
        mouveStock.setTypeMouveStk(mouveStockDto.getTypeMouveStk());
        return mouveStock;
    }
}

package com.damo.gestionDeStock.dto;


import com.damo.gestionDeStock.model.Article;
import lombok.Data;
import lombok.Builder;

import java.math.BigDecimal;

@Data
@Builder
public class
ArticleDto {

    private Integer id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHT;

    private BigDecimal tauxTVA;

    private BigDecimal prixUnitaireTTC;

    private String photo;

    private CategoryDto category;

    private Integer idEntreprise;

    public static ArticleDto fromEntity(Article article) {

        if (article == null) {
            return null;
            // Todo throw an Exception
        }
        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .prixUnitaireHT(article.getPrixUnitaireHT())
                .tauxTVA(article.getTauxTVA())
                .prixUnitaireTTC(article.getPrixUnitaireTTC())
                .photo(article.getPhoto())
                .category(CategoryDto.fromEntity(article.getCategory()))
                .idEntreprise(article.getIdEntreprise())
                .build();
    }

    public static Article toEntity(ArticleDto articleDto) {
        if (articleDto == null) {
            return null;
            // Todo throw an Exception
        }
        Article article = new Article();

        article.setId(articleDto.getId());
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaireHT(articleDto.getPrixUnitaireHT());
        article.setTauxTVA(articleDto.getTauxTVA());
        article.setPrixUnitaireTTC(articleDto.getPrixUnitaireTTC());
        article.setPhoto(articleDto.getPhoto());
        article.setIdEntreprise(articleDto.getIdEntreprise());
        article.setCategory(CategoryDto.toEntity(articleDto.getCategory()));
        return article;
    }
}

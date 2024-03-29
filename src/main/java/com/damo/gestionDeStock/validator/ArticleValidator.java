package com.damo.gestionDeStock.validator;

import com.damo.gestionDeStock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {
    public  static List<String> validator(ArticleDto articleDto) {
        List<String> errors = new ArrayList<>();

        if (articleDto ==null) {
            errors.add("Veuillez renseigner le code de l'article.");
            errors.add("Veuillez renseigner la designation de l'article.");
            errors.add("Veuillez renseigner le prix unitaire TH de l'article.");
            errors.add("Veuillez renseigner le prix unitaire TTC de l'article.");
            errors.add("Veuillez renseigner le taux TVA de l'article.");
            errors.add("Veuillez séléctionner une category.");
            return errors;
        }

        if (!StringUtils.hasLength(articleDto.getCodeArticle())){
            errors.add("Veuillez renseigner le code de l'article.");
        }
        if (!StringUtils.hasLength(articleDto.getDesignation())){
            errors.add("Veuillez renseigner la designation de l'article.");
        }
        if (articleDto.getPrixUnitaireHT() == null){
            errors.add("Veuillez renseigner le prix unitaire TH de l'article.");
        }
        if (articleDto.getPrixUnitaireTTC() == null){
            errors.add("Veuillez renseigner le prix unitaire TTC de l'article.");
        }
        if (articleDto.getTauxTVA() == null){
            errors.add("Veuillez renseigner le taux TVA de l'article.");
        }
        if (articleDto.getCategory() == null || articleDto.getCategory().getId() == null){
            errors.add("Veuillez séléctionner une category.");
        }



        return errors;
    }
}

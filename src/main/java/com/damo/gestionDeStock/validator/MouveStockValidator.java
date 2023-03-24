package com.damo.gestionDeStock.validator;

import com.damo.gestionDeStock.dto.MouveStockDto;

import java.util.ArrayList;
import java.util.List;

public class MouveStockValidator {

    public static List<String> validator(MouveStockDto mouveStockDto) {

        List<String> errors = new ArrayList<>();

        if (mouveStockDto == null){
            errors.add("Veuillez renseigner la quantité de mouvement de stock");
            errors.add("Veuillez renseigner la date du mouvement de stock");
            errors.add("Veuillez renseigner l'article du mouvement de stock");

            return  errors;
        }

        if (mouveStockDto.getQuantite() == null){
            errors.add("Veuillez renseigner la quantité de ligne de vente");
        }
        if (mouveStockDto.getDateMouveStock() == null){
            errors.add("Veuillez renseigner la date du mouvement de stock");
        }

        if (mouveStockDto.getArticle() == null) {
            errors.add("Veuillez renseigner l'article du mouvement de stock");
        }

        return errors;
    }

}

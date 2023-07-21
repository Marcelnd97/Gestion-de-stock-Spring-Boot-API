package com.damo.gestionDeStock.validator;

import com.damo.gestionDeStock.dto.MouveStockDto;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MouveStockValidator {

    public static List<String> validator(MouveStockDto mouveStockDto) {

        List<String> errors = new ArrayList<>();

        if (mouveStockDto == null) {
            errors.add("Veuillez renseigner la date du mouvenent");
            errors.add("Veuillez renseigner la quantite du mouvenent");
            errors.add("Veuillez renseigner l'article");
            errors.add("Veuillez renseigner le type du mouvement");

            return errors;
        }
        if (mouveStockDto.getDateMouveStock() == null) {
            errors.add("Veuillez renseigner la date du mouvenent");
        }
        if (mouveStockDto.getQuantite() == null || mouveStockDto.getQuantite().compareTo(BigDecimal.ZERO) == 0) {
            errors.add("Veuillez renseigner la quantite du mouvenent");
        }
        if (mouveStockDto.getArticle() == null || mouveStockDto.getArticle().getId() == null) {
            errors.add("Veuillez renseigner l'article");
        }
        if (!StringUtils.hasLength(mouveStockDto.getTypeMouveStk().name())) {
            errors.add("Veuillez renseigner le type du mouvement");
        }

        return errors;
    }

}

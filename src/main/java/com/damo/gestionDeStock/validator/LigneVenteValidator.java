package com.damo.gestionDeStock.validator;

import com.damo.gestionDeStock.dto.LigneVentesDto;

import java.util.ArrayList;
import java.util.List;

public class LigneVenteValidator {

    public static List<String> validator(LigneVentesDto ligneVentesDto) {

        List<String> errors = new ArrayList<>();

        if (ligneVentesDto == null){
            errors.add("Veuillez renseigner la quantité de ligne de vente");
            errors.add("Veuillez renseigner le prix unitaire de la ligne de vente");

            return  errors;
        }

        if (ligneVentesDto.getQuantite() == null){
            errors.add("Veuillez renseigner la quantité de ligne de vente");
        }
        if (ligneVentesDto.getPrixUnitaire() == null){
            errors.add("Veuillez renseigner le prix unitaire de la ligne de vente");
        }

        return errors;
    }
}

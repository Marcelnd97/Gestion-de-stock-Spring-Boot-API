package com.damo.gestionDeStock.validator;

import com.damo.gestionDeStock.dto.LigneCommandeClientDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeClientValidator {

    public static List<String> validator(LigneCommandeClientDto ligneCommandeClientDto) {

        List<String> errors = new ArrayList<>();

        if (ligneCommandeClientDto == null){
            errors.add("Veuillez renseigner la quantité de ligne de commande client");
            errors.add("Veuillez renseigner le prix unitaire de la ligne de commande client");

            return  errors;
        }

        if (ligneCommandeClientDto.getQuantite() == null){
            errors.add("Veuillez renseigner la quantité de ligne de commande client");
        }
        if (ligneCommandeClientDto.getPrixUnitaire() == null){
            errors.add("Veuillez renseigner le prix unitaire de la ligne de commande client");
        }

        return errors;
    }
}

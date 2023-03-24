package com.damo.gestionDeStock.validator;

import com.damo.gestionDeStock.dto.CommandeFournisseurDto;
import com.damo.gestionDeStock.dto.LigneCommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeFournisseurValidator {

    public static List<String> validator(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {

        List<String> errors = new ArrayList<>();

        if (ligneCommandeFournisseurDto == null){
            errors.add("Veuillez renseigner la quantité de ligne de commande fournisseur");
            errors.add("Veuillez renseigner le prix unitaire de la ligne de commande fournisseur");

            return  errors;
        }

        if (ligneCommandeFournisseurDto.getQuantite() == null){
            errors.add("Veuillez renseigner la quantité de ligne de commande fournisseur");
        }
        if (ligneCommandeFournisseurDto.getPrixUnitaire() == null){
            errors.add("Veuillez renseigner le prix unitaire de la ligne de commande fournisseur");
        }

        return errors;
    }
}

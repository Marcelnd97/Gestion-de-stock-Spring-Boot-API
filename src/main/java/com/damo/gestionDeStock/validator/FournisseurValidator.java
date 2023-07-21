package com.damo.gestionDeStock.validator;

import com.damo.gestionDeStock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {
    public static List<String> validator(FournisseurDto fournisseurDto) {
        List<String> errors = new ArrayList<>();

        if (fournisseurDto == null) {
            errors.add("Veuillez renseigner le nom du fournisseur");
            errors.add("Veuillez renseigner le prenom du fournisseur");
            errors.add("Veuillez renseigner le Mail du fournisseur");
            errors.add("Veuillez renseigner le numero de telephone du fournisseur");
            errors.addAll(AdresseValidator.validator(null));
            return errors;
        }

        if (!StringUtils.hasLength(fournisseurDto.getNom())) {
            errors.add("Veuillez renseigner le nom du fournisseur");
        }
        if (!StringUtils.hasLength(fournisseurDto.getPrenom())) {
            errors.add("Veuillez renseigner le prenom du fournisseur");
        }
        if (!StringUtils.hasLength(fournisseurDto.getMail())) {
            errors.add("Veuillez renseigner le Mail du fournisseur");
        }
        if (!StringUtils.hasLength(fournisseurDto.getNumTel())) {
            errors.add("Veuillez renseigner le numero de telephone du fournisseur");
        }
        errors.addAll(AdresseValidator.validator(fournisseurDto.getAdresse()));
        return errors;
    }
}

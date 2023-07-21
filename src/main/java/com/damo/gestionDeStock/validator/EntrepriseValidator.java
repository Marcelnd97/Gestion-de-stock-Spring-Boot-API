package com.damo.gestionDeStock.validator;

import com.damo.gestionDeStock.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {
    public static List<String> validator(EntrepriseDto entrepriseDto) {
        List<String> errors = new ArrayList<>();

        if (entrepriseDto == null) {
            errors.add("Veuillez renseigner le nom de l'entreprise");
            errors.add("Veuillez reseigner la description de l'entreprise");
            errors.add("Veuillez reseigner le code fiscal de l'entreprise");
            errors.add("Veuillez reseigner l'email de l'entreprise");
            errors.add("Veuillez reseigner le numero de telephone de l'entreprise");
            errors.addAll(AdresseValidator.validator(null));
            return errors;
        }

        if (!StringUtils.hasLength(entrepriseDto.getNom())) {
            errors.add("Veuillez renseigner le nom de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getDescription())) {
            errors.add("Veuillez reseigner la description de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getCodeFiscal())) {
            errors.add("Veuillez reseigner le code fiscal de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getEmail())) {
            errors.add("Veuillez reseigner l'email de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getNumTel())) {
            errors.add("Veuillez reseigner le numero de telephone de l'entreprise");
        }

        errors.addAll(AdresseValidator.validator(entrepriseDto.getAdresse()));

        return errors;
    }
}

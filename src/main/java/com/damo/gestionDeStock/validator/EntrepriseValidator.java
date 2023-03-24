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
            errors.add("Veuillez renseigner la description de l'entreprise");
            errors.add("Veuillez renseigner le mail de l'entreprise");
            errors.add("Veuillez renseigner le numéro téléphone de l'entreprise");
            errors.add("Veuillez renseigner le code fiscale de l'entreprise");
            errors.add("Veuillez renseigner le site de l'entreprise");
            errors.add("Veuillez renseigner l'adresse de l'entreprise.");
            
            return errors;
        }

        if (!StringUtils.hasLength(entrepriseDto.getNom())){
            errors.add("Veuillez renseigner le nom de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getDescription())){
            errors.add("Veuillez renseigner la description de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getEmail())){
            errors.add("Veuillez renseigner le mail de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getNumTel())){
            errors.add("Veuillez renseigner le numéro téléphone de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getCodeFiscale())){
            errors.add("Veuillez renseigner le code fiscale de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getSiteWeb())){
            errors.add("Veuillez renseigner le site de l'entreprise");
        }
        if (entrepriseDto.getAdresse() == null){
            errors.add("Veuillez renseigner l'adresse de l'entreprsie.");
        }else {

            if (!StringUtils.hasLength(entrepriseDto.getAdresse().getAdresse1())){
                errors.add("Veuillez renseigner l'adresse1 de l'entreprsie.");
            }
            if (!StringUtils.hasLength(entrepriseDto.getAdresse().getVille())){
                errors.add("Veuillez renseigner la ville de l'entreprsie.");
            }
            if (!StringUtils.hasLength(entrepriseDto.getAdresse().getCodePostale())){
                errors.add("Veuillez renseigner la code postal de l'entreprsie.");
            }
            if (!StringUtils.hasLength(entrepriseDto.getAdresse().getPays())){
                errors.add("Veuillez renseigner la pays de l'entreprsie.");
            }
        }

        return errors;
    }
}

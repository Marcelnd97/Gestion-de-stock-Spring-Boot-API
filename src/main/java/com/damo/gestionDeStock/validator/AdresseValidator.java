package com.damo.gestionDeStock.validator;

import com.damo.gestionDeStock.dto.AdresseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AdresseValidator {

    public static List<String> validator(AdresseDto adresseDto) {
        List<String> errors = new ArrayList<>();
        if (adresseDto == null) {
            errors.add("Veuillez renseigner l'adresse 1'");
            errors.add("Veuillez renseigner la ville'");
            errors.add("Veuillez renseigner le pays");
            errors.add("Veuillez renseigner le code postale");

            return errors;
        }

        if (!StringUtils.hasLength(adresseDto.getAdresse1())) {
            errors.add("Veuillez renseigner l'adresse 1'");
        }
        if (!StringUtils.hasLength(adresseDto.getVille())) {
            errors.add("Veuillez renseigner la ville'");
        }

        if (!StringUtils.hasLength(adresseDto.getPays())){
            errors.add("Veuillez renseigner le pays");
        }
        if (!StringUtils.hasLength(adresseDto.getCodePostale())){
            errors.add("Veuillez renseigner le code postale");
        }

        return errors;
    }
}

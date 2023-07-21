package com.damo.gestionDeStock.validator;

import com.damo.gestionDeStock.dto.VenteDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VenteValidator {

    public static List<String> validator(VenteDto venteDto) {
        List<String> errors = new ArrayList<>();

        if (venteDto == null) {
            errors.add("Veuillez renseigner le code de vente");
            errors.add("Veuillez renseigner la date de vente");

            return errors;
        }

        if (!StringUtils.hasLength(venteDto.getCode())){
            errors.add("Veuillez renseigner le code de vente");
        }
        if (venteDto.getDateVente() == null){
            errors.add("Veuillez renseigner la date de vente");
        }

        return errors;
    }

}

package com.damo.gestionDeStock.validator;

import com.damo.gestionDeStock.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validator(ClientDto clientDto) {
        List<String> errors = new ArrayList<>();

        if (clientDto == null) {
            errors.add("Veuillez renseigner le nom du client.");
            errors.add("Veuillez renseigner le prenom du client");
            errors.add("Veuillez renseigner le mail du client");
            errors.add("Veuillez renseigner le numéro de téléphone du client");
            errors.addAll(AdresseValidator.validator(null));
            return errors;
        }

        if (!StringUtils.hasLength(clientDto.getNom())) {
            errors.add("Veuillez renseigner le nom du client");
        }
        if (!StringUtils.hasLength(clientDto.getPrenom())) {
            errors.add("Veuillez renseigner le prenom du client");
        }
        if (!StringUtils.hasLength(clientDto.getMail())) {
            errors.add("Veuillez renseigner le Mail du client");
        }
        if (!StringUtils.hasLength(clientDto.getNumTel())) {
            errors.add("Veuillez renseigner le numero de telephone du client");
        }
        errors.addAll(AdresseValidator.validator(clientDto.getAdresse()));
        return errors;
    }
}

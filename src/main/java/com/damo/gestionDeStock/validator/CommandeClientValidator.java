package com.damo.gestionDeStock.validator;

import com.damo.gestionDeStock.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {

    public static List<String> validator(CommandeClientDto commandeClientDto) {

        List<String> errors = new ArrayList<>();

        if (commandeClientDto == null) {
            errors.add("Veuillez renseigner le code de la commande client");
            errors.add("Veuillez renseigner la date de la commande client");

            return errors;
        }

        if (!StringUtils.hasLength(commandeClientDto.getCode())){
            errors.add("Veuillez renseigner le code de la commande client");
        }
        if (commandeClientDto.getDateCommande() == null){
            errors.add("Veuillez renseigner la date de la commande client");
        }

        return errors;
    }
}

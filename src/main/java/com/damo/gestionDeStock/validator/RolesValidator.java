package com.damo.gestionDeStock.validator;

import com.damo.gestionDeStock.dto.RolesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RolesValidator {

    public static List<String> validator(RolesDto rolesDto) {

        List<String> errors = new ArrayList<>();

        if (rolesDto == null){
            errors.add("Veuillez renseigner le nom du role");
            errors.add("Veuillez renseigner l'identité de l'utilisateur");

            return  errors;
        }

        if (!StringUtils.hasLength(rolesDto.getRoleName())){
            errors.add("Veuillez renseigner le nom du role");
        }
        if (rolesDto.getUtilisateur() == null){
            errors.add("Veuillez renseigner l'identité de l'utilisateur");
        }

        return errors;
    }

}

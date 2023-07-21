package com.damo.gestionDeStock.validator;

import com.damo.gestionDeStock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {
    public static List<String> validator(UtilisateurDto utilisateurDto) {
        List<String> errors = new ArrayList<>();

        if (utilisateurDto == null) {
            errors.add("Veuillez renseigner le nom d'utilisateur");
            errors.add("Veuillez renseigner le prenom d'utilisateur");
            errors.add("Veuillez renseigner le mot de passe d'utilisateur");
            errors.add("Veuillez renseigner l'adresse d'utilisateur");
            errors.addAll(AdresseValidator.validator(null));
            return errors;
        }

        if (!StringUtils.hasLength(utilisateurDto.getNom())) {
            errors.add("Veuillez renseigner le nom d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getPrenom())) {
            errors.add("Veuillez renseigner le prenom d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getEmail())) {
            errors.add("Veuillez renseigner l'email d'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getMotDePasse())) {
            errors.add("Veuillez renseigner le mot de passe d'utilisateur");
        }
        if (utilisateurDto.getDateDeNaissance() == null) {
            errors.add("Veuillez renseigner la date de naissance d'utilisateur");
        }
        errors.addAll(AdresseValidator.validator(utilisateurDto.getAdresse()));

        return errors;
    }
}

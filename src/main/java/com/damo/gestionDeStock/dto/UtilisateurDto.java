package com.damo.gestionDeStock.dto;

import com.damo.gestionDeStock.model.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class UtilisateurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String mail;

    private Instant datenaissance;

    private String motDePasse;

    private AdresseDto adresse;

    private EntrepriseDto entreprise;

    private String photo;

    @JsonIgnore
    private List<RolesDto> roles;

    private Integer idEntreprise;


    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
            // Todo throw an Exception
        }
        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
                .entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                .idEntreprise(utilisateur.getIdEntreprise())
                .motDePasse(utilisateur.getMotDePasse())
                .photo(utilisateur.getPhoto())
                .mail(utilisateur.getMail())
                .datenaissance(utilisateur.getDatenaissance())
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto) {
        if (utilisateurDto == null) {
            return null;
            // Todo throw an Exception
        }

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setMail(utilisateurDto.getMail());
        utilisateur.setDatenaissance(utilisateurDto.getDatenaissance());
        utilisateur.setIdEntreprise(utilisateurDto.getIdEntreprise());
        return utilisateur;
    }

    public static UtilisateurDto fromEntreprise(EntrepriseDto savedEntreprise) {
        return UtilisateurDto.builder()
                .adresse(savedEntreprise.getAdresse())
                .nom(savedEntreprise.getNom())
                .prenom(savedEntreprise.getCodeFiscale())
                .mail(savedEntreprise.getEmail())
                .motDePasse(generateRandomPassword())
                .entreprise(savedEntreprise)
                .datenaissance(Instant.now())
                .photo(savedEntreprise.getPhoto())
                .build();
    }

    private static String generateRandomPassword(){
        return "som3R@nd0mP@$$word";
    }

}

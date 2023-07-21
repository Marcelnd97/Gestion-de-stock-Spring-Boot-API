package com.damo.gestionDeStock.dto;

import com.damo.gestionDeStock.model.CommandeFournisseur;
import com.damo.gestionDeStock.model.EtatCommande;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeFournisseurDto {

    private Integer id;

    private String code;

    private EtatCommande etatCommande;

    private Instant dateCommande;

    private FournisseurDto fournisseur;

    private Integer idEntreprise;

    private List<LigneCommandeFournisseurDto> lignecommandeFournisseur;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
        if (commandeFournisseur == null) {
            return null;
            // Todo throw an Exception
        }

        return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
                .etatCommande(commandeFournisseur.getEtatCommande())
                .dateCommande(commandeFournisseur.getDateCommande())
                .fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
                .idEntreprise(commandeFournisseur.getIdEntreprise())
                .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto commandeFournisseurDto) {
        if (commandeFournisseurDto == null) {
            return null;
            // Todo throw an Exception
        }
        CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
        commandeFournisseur.setId(commandeFournisseurDto.getId());
        commandeFournisseur.setCode(commandeFournisseurDto.getCode());
        commandeFournisseur.setEtatCommande(commandeFournisseur.getEtatCommande());
        commandeFournisseur.setDateCommande(commandeFournisseurDto.getDateCommande());
        commandeFournisseur.setIdEntreprise(commandeFournisseur.getIdEntreprise());
        return commandeFournisseur;
    }

    public Boolean isCommandeLivree(){
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }
}

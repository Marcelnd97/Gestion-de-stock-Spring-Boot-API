package com.damo.gestionDeStock.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangerMotDePasseUtilisateurDto {

    private Integer id;
    private String motDePasse;
    private String confirmeMotDePasse;
}

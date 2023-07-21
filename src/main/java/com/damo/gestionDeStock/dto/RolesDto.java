package com.damo.gestionDeStock.dto;

import com.damo.gestionDeStock.model.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolesDto {

    private Integer id;

    private String roleName;

    @JsonIgnore
    private UtilisateurDto utilisateur;


    public static RolesDto fromEntity(Roles roles) {
        if (roles == null) {
            return null;
            // Todo throw an Exception
        }

        return RolesDto.builder()
                .id(roles.getId())
                .roleName(roles.getRoleName())
                .build();
    }

    public static Roles toEntity(RolesDto roles) {
        if (roles == null) {
            return null;
            // Todo throw an Exception
        }
        Roles roles1 = new Roles();

        roles1.setId(roles.getId());
        roles1.setRoleName(roles.getRoleName());
        roles1.setUtilisateur(UtilisateurDto.toEntity(roles.getUtilisateur()));
        return roles1;
    }
}

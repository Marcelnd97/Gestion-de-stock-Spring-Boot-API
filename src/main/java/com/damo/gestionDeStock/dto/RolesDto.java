package com.damo.gestionDeStock.dto;

import com.damo.gestionDeStock.model.Roles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolesDto {

    private Integer id;

    private String roleName;

    private UtilisateurDto utilisateur;


    public RolesDto fromEntity(Roles roles) {
        if (roles == null) {
            return null;
            // Todo throw an Exception
        }

        return RolesDto.builder()
                .id(roles.getId())
                .roleName(roles.getRoleName())
                .utilisateur(UtilisateurDto.fromEntity(roles.getUtilisateur()))
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
        return roles1;
    }
}

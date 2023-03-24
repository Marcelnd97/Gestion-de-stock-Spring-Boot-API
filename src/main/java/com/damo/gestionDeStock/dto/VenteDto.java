package com.damo.gestionDeStock.dto;


import com.damo.gestionDeStock.model.Ventes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VenteDto {

    private Integer id;

    private String code;

    private Instant dateVente;

    private String commantaire;

    private Integer idEntreprise;

    @JsonIgnore
    private List<LigneVentesDto> ligneVentes;

    public static VenteDto fromEntity(Ventes ventes) {
        if (ventes == null) {
            return null;
            // Todo throw an Exception
        }

        return VenteDto.builder()
                .id(ventes.getId())
                .code(ventes.getCode())
                .dateVente(ventes.getDateVente())
                .commantaire(ventes.getCommantaire())
                .idEntreprise(ventes.getIdEntreprise())
                .build();
    }

    public static Ventes toEntity(VenteDto ventesDto) {
        if (ventesDto == null) {
            return null;
            // Todo throw an Exception
        }
        Ventes ventes1 = new Ventes();

        ventes1.setId(ventesDto.getId());
        ventes1.setCode(ventesDto.getCode());
        ventes1.setDateVente(ventesDto.getDateVente());
        ventes1.setCommantaire(ventesDto.getCommantaire());
        ventes1.setIdEntreprise((ventesDto.getIdEntreprise()));
        return ventes1;
    }
}

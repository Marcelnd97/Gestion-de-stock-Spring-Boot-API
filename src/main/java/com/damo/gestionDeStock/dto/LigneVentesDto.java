package com.damo.gestionDeStock.dto;

import com.damo.gestionDeStock.model.LigneVente;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class LigneVentesDto {

    private Integer id;

    private VenteDto vente;

    private ArticleDto article;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;

    public static LigneVentesDto fromEntity(LigneVente ligneVente) {
        if (ligneVente == null) {
            return null;
            // Todo throw an Exception
        }

        return LigneVentesDto.builder()
                .id(ligneVente.getId())
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .vente(VenteDto.fromEntity(ligneVente.getVente()))
                .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                .idEntreprise(ligneVente.getIdEntreprise())
                .build();
    }

    public static LigneVente toEntity(LigneVentesDto ligneVenteDto) {
        if (ligneVenteDto == null) {
            return null;
            // Todo throw an Exception
        }
        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setQuantite(ligneVenteDto.getQuantite());
        ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());
        ligneVente.setIdEntreprise(ligneVente.getIdEntreprise());
        return ligneVente;
    }
}

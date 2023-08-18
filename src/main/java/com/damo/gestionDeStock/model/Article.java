package com.damo.gestionDeStock.model;

import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "article")
public class Article extends AbstractEntity{

    @Column(name = "codearticle")
    private String codeArticle;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @Column(name = "totalStock")
    private BigDecimal totalStock;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prixunitaireht")
    private BigDecimal prixUnitaireHT;

    @Column(name = "tauxtva")
    private BigDecimal tauxTVA;

    @Column(name = "prixunitairettc")
    private BigDecimal prixUnitaireTTC;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "idcategory")
    private Category category;

    @OneToMany(mappedBy = "article")
    private List<LigneVente> ligneVentes;

    @OneToMany(mappedBy = "article")
    private List<LigneCommandeClient> ligneCommandeClients;

    @OneToMany(mappedBy = "article")
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;

    @OneToMany(mappedBy = "article")
    private List<MouveStock> mouveStocks;

}

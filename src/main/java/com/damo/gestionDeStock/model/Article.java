package com.damo.gestionDeStock.model;

import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "article")
public class Article extends AbstractEntity{

    @Column(name = "codearticle")
    private String codeArticle;

//    @Column(name = "identreprise")
//    private Integer idEntreprise;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idcategory")
    private Category category;

}

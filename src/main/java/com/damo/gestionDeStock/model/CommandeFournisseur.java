package com.damo.gestionDeStock.model;

import javax.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "commandeFournisseur")
public class CommandeFournisseur extends  AbstractEntity{

    @Column(name = "code")
    private String code;

    @Column(name = "etatCommande")
    private EtatCommande etatCommane;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @Column(name = "datecommande")
    private Instant dateCommande;

    @ManyToOne
    @JoinColumn(name = "idfournisseur")
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "commandeFournisseur", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LigneCommandeFournisseur> lignecommandeFournisseur;
}

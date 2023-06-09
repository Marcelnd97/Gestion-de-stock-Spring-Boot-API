
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
@Table(name = "commandeClient")
public class CommandeClient extends  AbstractEntity{

    @Column(name = "code")
    private String code;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @Column(name = "datecommande")
    private Instant dateCommande;

    @Column(name = "etatCommande")
    private EtatCommande etatCommande;

    @ManyToOne
    @JoinColumn(name = "idclient")
    private Client client;

    @OneToMany(mappedBy = "commandeClient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LigneCommandeClient> ligneCommandeClients;

}

package com.damo.gestionDeStock.model;

import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "mouveStock")
public class MouveStock extends AbstractEntity{

    @Column(name = "datemouvedtock")
    private Instant dateMouveStock;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idarticle")
    private Article article;

//    @Column(name = "typemouvestk")
//    private TypeMouveStkDto typeMouveStk;

    @Column(name = "identreprise")
    private Integer idEntreprise;

}

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

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @Column(name = "typemvstk")
    @Enumerated(EnumType.STRING)
    private TypeMouveStk typeMouveStk;

    @Column(name = "sourcemvt")
    @Enumerated(EnumType.STRING)
    private SourceMvt sourceMvt;

    @Column(name = "identreprise")
    private Integer idEntreprise;

}

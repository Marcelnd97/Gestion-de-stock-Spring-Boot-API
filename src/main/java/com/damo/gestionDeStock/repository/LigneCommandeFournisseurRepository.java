package com.damo.gestionDeStock.repository;

import com.damo.gestionDeStock.model.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Integer> {
    List<LigneCommandeFournisseur> findAllCommandeFournisseurById(Integer id);

    List<LigneCommandeFournisseur> findAllByArticleId(Integer idArticle);
}

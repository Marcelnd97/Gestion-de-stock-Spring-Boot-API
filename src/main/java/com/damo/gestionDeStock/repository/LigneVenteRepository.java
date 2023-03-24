package com.damo.gestionDeStock.repository;

import com.damo.gestionDeStock.dto.ArticleDto;
import com.damo.gestionDeStock.model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer> {
    List<LigneVente> findAllByArticleId(Integer idArticle);


}

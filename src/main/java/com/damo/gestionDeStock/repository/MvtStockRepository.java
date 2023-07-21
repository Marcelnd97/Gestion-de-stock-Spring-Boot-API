package com.damo.gestionDeStock.repository;

import com.damo.gestionDeStock.model.MouveStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStockRepository extends JpaRepository<MouveStock, Integer> {

    @Query("select sum(m.quantite) from MouveStock m where m.article.id = :idArticle")
    BigDecimal stockReelArticle(Integer idArticle);
    List<MouveStock> findAllByArticleId(Integer idArticle);
}

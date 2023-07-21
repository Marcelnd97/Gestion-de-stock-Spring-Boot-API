package com.damo.gestionDeStock.repository;

import com.damo.gestionDeStock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {
    List<LigneCommandeClient> findAllByCommandeClientId(Integer id);

    List<LigneCommandeClient> findAllByArticleId(Integer idArticle);
}

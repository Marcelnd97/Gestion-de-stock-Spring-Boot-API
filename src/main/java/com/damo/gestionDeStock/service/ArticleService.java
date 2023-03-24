package com.damo.gestionDeStock.service;


import com.damo.gestionDeStock.dto.ArticleDto;
import com.damo.gestionDeStock.dto.LigneCommandeClientDto;
import com.damo.gestionDeStock.dto.LigneCommandeFournisseurDto;
import com.damo.gestionDeStock.dto.LigneVentesDto;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleDto artiDto);
    ArticleDto findById(Integer id);
    ArticleDto findByCodeArticle(String codeArticle);
    List<ArticleDto> findAll();
    List<LigneVentesDto> findHistoriqueVente(Integer idArticle);
    List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle);
    List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);
    List<ArticleDto> findArticleByIdCategory(Integer idCategory);

    void delete(Integer id);
}

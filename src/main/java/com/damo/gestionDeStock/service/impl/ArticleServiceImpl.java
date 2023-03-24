package com.damo.gestionDeStock.service.impl;


import com.damo.gestionDeStock.dto.ArticleDto;
import com.damo.gestionDeStock.dto.LigneCommandeClientDto;
import com.damo.gestionDeStock.dto.LigneCommandeFournisseurDto;
import com.damo.gestionDeStock.dto.LigneVentesDto;
import com.damo.gestionDeStock.exception.EntityNotFoundException;
import com.damo.gestionDeStock.exception.ErrorCodes;
import com.damo.gestionDeStock.exception.InvalidEntityException;
import com.damo.gestionDeStock.model.Article;
import com.damo.gestionDeStock.repository.ArticleRepository;
import com.damo.gestionDeStock.repository.LigneCommandeClientRepository;
import com.damo.gestionDeStock.repository.LigneCommandeFournisseurRepository;
import com.damo.gestionDeStock.repository.LigneVenteRepository;
import com.damo.gestionDeStock.service.ArticleService;
import com.damo.gestionDeStock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {


    private ArticleRepository articleRepository;
    private LigneVenteRepository ligneVenteRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository,
                              LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository,
                              LigneCommandeClientRepository ligneCommandeClientRepository,
                              LigneVenteRepository ligneVenteRepository){

        this.articleRepository = articleRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public ArticleDto save(ArticleDto artiDto) {
        List<String> errors = ArticleValidator.validator(artiDto);

        if (!errors.isEmpty()) {
            log.error("Article is not valid {}", artiDto);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(artiDto)));
    }

    @Override
    public ArticleDto findById(Integer id) {

        if (id == null){
            log.error("Artice ID is null");
            return  null;
        }

        Optional<Article> article = articleRepository.findById(id);

        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun article avec l'ID =" + id + "n'est trouver dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {

        if (codeArticle == null){
            log.error("Article CODE is null");
            return  null;
        }

        Optional<Article> article = articleRepository.findByCodeArticle(codeArticle);

        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun article avec le CODE =" + codeArticle + "n'été trouver dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("Article ID is null");
            return;
        }
        articleRepository.findById(id);
    }

    @Override
    public List<LigneVentesDto> findHistoriqueVente(Integer idArticle) {
        return ligneVenteRepository.findAllByArticleId(idArticle).stream()
                .map(LigneVentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle) {
        return ligneCommandeClientRepository.findAllByArticleId(idArticle).stream()
                .map(LigneCommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
        return ligneCommandeFournisseurRepository.findAllByArticleId(idArticle).stream()
                .map(LigneCommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findArticleByIdCategory(Integer idCategory) {
        return articleRepository.findAlArticleByCategoryId(idCategory).stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }
}

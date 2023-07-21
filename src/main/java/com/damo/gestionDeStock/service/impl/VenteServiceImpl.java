package com.damo.gestionDeStock.service.impl;

import com.damo.gestionDeStock.dto.*;
import com.damo.gestionDeStock.handlers.exception.EntityNotFoundException;
import com.damo.gestionDeStock.handlers.exception.ErrorCodes;
import com.damo.gestionDeStock.handlers.exception.InvalidEntityException;
import com.damo.gestionDeStock.handlers.exception.InvalideOperationException;
import com.damo.gestionDeStock.model.*;
import com.damo.gestionDeStock.repository.*;
import com.damo.gestionDeStock.service.MouveStkService;
import com.damo.gestionDeStock.service.VenteService;
import com.damo.gestionDeStock.validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements VenteService {

    private VenteRepository venteRepository;

    private ArticleRepository articleRepository;

    private MouveStkService mouveStkService;

    private final LigneVenteRepository ligneVenteRepository;

    @Autowired
    public VenteServiceImpl(VenteRepository venteRepository, ArticleRepository articleRepository, MouveStkService mouveStkService, LigneVenteRepository ligneVenteRepository) {
        this.venteRepository = venteRepository;
        this.articleRepository = articleRepository;
        this.mouveStkService = mouveStkService;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VenteDto save(VenteDto venteDto) {
        List<String> errors = VenteValidator.validator(venteDto);

        if (!errors.isEmpty()){
            log.error("Vente is not valid");
            throw new InvalidEntityException("La Vente n'est pas valide", ErrorCodes.VENTE_NOT_FOUND);
        }
        List<String> articleErrors = new ArrayList<>();

        venteDto.getLigneVentes().forEach(ligneVentesDto -> {

            Optional<Article> article = articleRepository.findById(ligneVentesDto.getArticle().getId());
         if (article.isEmpty()) {
             articleErrors.add("L'article avec l'ID" + ligneVentesDto.getArticle().getId() + "n'existe pas.");
         }
         });

        if (!articleErrors.isEmpty()){
            log.error("One or more articles were not found in the DB, {}", errors);
            throw new InvalidEntityException("Un ou plusieur article n'ont pas été retrouvé dans la BDD", ErrorCodes.VENTE_NOT_FOUND, articleErrors);
        }

        Ventes saveVente = venteRepository.save(VenteDto.toEntity(venteDto));

        venteDto.getLigneVentes().forEach(ligVente ->{
            LigneVente ligneVente = LigneVentesDto.toEntity(ligVente);
            ligneVente.setVente(saveVente);
            ligneVenteRepository.save(ligneVente);
            updateMvtStk(ligneVente);
        });
        return  VenteDto.fromEntity(saveVente);
    }

    @Override
    public VenteDto findById(Integer id) {

        if (id == null){
            log.error("Vente ID is null.");
            return null;
        }
        return venteRepository.findById(id)
                .map(VenteDto::fromEntity).orElseThrow(() ->
                        new EntityNotFoundException("Aucune vente n'a ete retrouvé avec l'ID" + id, ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public VenteDto findVenteByCode(String codeVente) {

        if (codeVente == null){
            log.error("Vente CODE is null.");
            return null;
        }
        Optional<Ventes> ventes = venteRepository.findVenteByCode(codeVente);

        return Optional.of(VenteDto.fromEntity(ventes.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucune vente avec le CODE =" + codeVente + "n'a été retrouver dans la BDD",
                        ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public List<VenteDto> findAll() {

        return venteRepository.findAll().stream()
                .map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("Utilisateur ID is null");
            return;
        }

        List<LigneVente> ligneVentes = ligneVenteRepository.findAllByVenteId(id);
        if (!ligneVentes.isEmpty()) {
            throw new InvalideOperationException("Impossible de supprimer une vente ...",
                    ErrorCodes.VENTE_ALREADY_IN_USE);
        }
        venteRepository.deleteById(id);

    }

    private void updateMvtStk(LigneVente lig) {
        MouveStockDto mvtStkDto = MouveStockDto.builder()
                .article(ArticleDto.fromEntity(lig.getArticle()))
                .dateMouveStock(Instant.now())
                .typeMouveStk(TypeMouveStk.SORTIE)
                .sourceMvt(SourceMvt.VENTE)
                .quantite(lig.getQuantite())
                .idEntreprise(lig.getIdEntreprise())
                .build();
        mouveStkService.sortieStock(mvtStkDto);
    }
}

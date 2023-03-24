package com.damo.gestionDeStock.service;

import com.damo.gestionDeStock.dto.CommandeFournisseurDto;
import com.damo.gestionDeStock.dto.CommandeFournisseurDto;
import com.damo.gestionDeStock.dto.CommandeFournisseurDto;
import com.damo.gestionDeStock.dto.LigneCommandeFournisseurDto;
import com.damo.gestionDeStock.model.CommandeFournisseur;
import com.damo.gestionDeStock.model.EtatCommande;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CommandeFournisseurService {

    CommandeFournisseurDto save(CommandeFournisseurDto cmdFrsDto);
    CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);
    CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantity);

    //Delete article ==> delete ligne de commande

    CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande);
    CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);
    CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur);
    CommandeFournisseurDto findById(Integer id);
    CommandeFournisseurDto findComFrsByCode(String codeComFrs);
    List<CommandeFournisseurDto> findAll();
    List<LigneCommandeFournisseurDto> findAllLineCommandeFournisseurByIdCommande(Integer idCommande);
    void deleteById(Integer id);
}

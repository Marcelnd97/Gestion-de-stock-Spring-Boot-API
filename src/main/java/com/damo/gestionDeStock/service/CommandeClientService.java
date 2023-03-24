package com.damo.gestionDeStock.service;

import com.damo.gestionDeStock.dto.CommandeClientDto;
import com.damo.gestionDeStock.dto.LigneCommandeClientDto;
import com.damo.gestionDeStock.model.EtatCommande;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto cmdCltDto);
    CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);
    CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantity);

    //Delete article ==> delete ligne de commande
    CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande);
    CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);
    CommandeClientDto updateClient(Integer idCommande, Integer idClient);
    CommandeClientDto findById(Integer id);
    CommandeClientDto findComClientByCode(String codeComClient);
    List<LigneCommandeClientDto> findAllLineCommandeClientByIdCommandeClient(Integer idCommande);
    List<CommandeClientDto> findAll();

    void delete(Integer id);
}

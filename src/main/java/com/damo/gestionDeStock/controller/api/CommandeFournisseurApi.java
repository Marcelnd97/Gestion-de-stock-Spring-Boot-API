package com.damo.gestionDeStock.controller.api;

import com.damo.gestionDeStock.dto.CommandeClientDto;
import com.damo.gestionDeStock.dto.CommandeFournisseurDto;
import com.damo.gestionDeStock.dto.LigneCommandeFournisseurDto;
import com.damo.gestionDeStock.model.EtatCommande;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.damo.gestionDeStock.utils.Constants.*;

@Api("commandefournisseur")
public interface CommandeFournisseurApi {

    @PostMapping(CREATE_COMMANDEFOURNISSEUR_ENDPOINT)
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto cmdFrsDto);

    @GetMapping(FIND_BY_ID_COMMANDEFOURNISSEUR_ENDPOINT)
    CommandeFournisseurDto findById(@PathVariable("idCommandeFournisseur") Integer id);

    @GetMapping(FIND_BY_CODE_COMMANDEFOURNISSEUR_ENDPOINT)
    CommandeFournisseurDto findComFrsByCode(@PathVariable("codeCommandeFournisseur") String codeComFrs);

    @GetMapping(FIND_ALL_COMMANDEFOURNISSEUR_ENDPOINT)
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(DELETE_BY_ID_COMMANDEFOURNISSEUR_ENDPOINT)
    void delete(@PathVariable("idCommandeFournisseur") Integer id);

    @PatchMapping(UPDATE_ETAT_COMMANDE_BY_ID_COMMANDE_AND_ETAT_COMMANDE_COMMANDE_FOURNISSEUR_ENDPOINT)
    ResponseEntity<CommandeFournisseurDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

    @PatchMapping(UPDATE_FOURNISSEUR_BY_ID_COMMANDE_AND_ID_FOURNISSEUR_COMMANDE_FOURNISSEUR_ENDPOINT)
    ResponseEntity<CommandeFournisseurDto> updateFournisseur(@PathVariable("idCommande") Integer idCommande, @PathVariable("idFournisseur") Integer idFournisseur);

    @PatchMapping(UPDATE_QUANTITY_COMMANDE_FOURNISSEUR_ENDPOINT)
    ResponseEntity<CommandeClientDto> updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande,
                                                             @PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(UPDATE_ARTICLE_COMMANDE_FOURNISSEUR_ENDPOINT)
    ResponseEntity<CommandeFournisseurDto> updateArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);

    @GetMapping(FIND_ALL_BY_ID_COMMANDEFOURNISSEUR_ENDPOINT)
    ResponseEntity<List<LigneCommandeFournisseurDto>> findAllCommandeClientByIdCommandeClient(@PathVariable("idCommande") Integer idCommande);


    @DeleteMapping(DELETE_ARTICLE_COMMANDEFOURNISSEUR_ENDPOINT)
    ResponseEntity<CommandeFournisseurDto> deleteArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);

}

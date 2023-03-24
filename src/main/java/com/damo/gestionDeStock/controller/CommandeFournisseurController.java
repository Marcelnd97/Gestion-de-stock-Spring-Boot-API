package com.damo.gestionDeStock.controller;

import com.damo.gestionDeStock.controller.api.CommandeFournisseurApi;
import com.damo.gestionDeStock.dto.CommandeClientDto;
import com.damo.gestionDeStock.dto.CommandeFournisseurDto;
import com.damo.gestionDeStock.dto.LigneCommandeFournisseurDto;
import com.damo.gestionDeStock.model.EtatCommande;
import com.damo.gestionDeStock.service.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

    private final CommandeFournisseurService commandeFournisseurService;

    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto cmdFrsDto) {
        return commandeFournisseurService.save(cmdFrsDto);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        return commandeFournisseurService.findById(id);
    }

    @Override
    public CommandeFournisseurDto findComFrsByCode(String codeComFrs) {
        return commandeFournisseurService.findComFrsByCode(codeComFrs);
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        commandeFournisseurService.deleteById(id);
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        return ResponseEntity.ok(commandeFournisseurService.updateEtatCommande(idCommande, etatCommande));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateFournisseur(Integer idCommande, Integer idFournisseur) {
        return ResponseEntity.ok(commandeFournisseurService.updateFournisseur(idCommande, idFournisseur));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return null;
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        return ResponseEntity.ok(commandeFournisseurService.updateArticle(idCommande, idLigneCommande, idArticle));
    }

    @Override
    public ResponseEntity<List<LigneCommandeFournisseurDto>> findAllCommandeClientByIdCommandeClient(Integer idCommande) {
        return ResponseEntity.ok(commandeFournisseurService.findAllLineCommandeFournisseurByIdCommande(idCommande));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> deleteArticle(Integer idCommande, Integer idLigneCommande) {
        return ResponseEntity.ok(commandeFournisseurService.deleteArticle(idCommande, idLigneCommande));
    }
}

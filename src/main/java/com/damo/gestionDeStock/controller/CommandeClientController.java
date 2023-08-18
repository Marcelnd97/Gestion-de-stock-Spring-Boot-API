package com.damo.gestionDeStock.controller;

import com.damo.gestionDeStock.controller.api.CommandeClientApi;
import com.damo.gestionDeStock.dto.CommandeClientDto;
import com.damo.gestionDeStock.dto.LigneCommandeClientDto;
import com.damo.gestionDeStock.model.EtatCommande;
import com.damo.gestionDeStock.service.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

    private final CommandeClientService commandeClientService;

    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto cmdCltDto) {
        return ResponseEntity.ok(commandeClientService.save(cmdCltDto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        return ResponseEntity.ok(commandeClientService.updateEtatCommande(idCommande, etatCommande));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateClient(Integer idCommande, Integer idClient) {
        return ResponseEntity.ok(commandeClientService.updateClient(idCommande, idClient));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return ResponseEntity.ok(commandeClientService.updateQuantiteCommande(idCommande, idLigneCommande, quantite));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        return ResponseEntity.ok(commandeClientService.updateArticle(idCommande, idLigneCommande, idArticle));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Integer id) {
        return ResponseEntity.ok(commandeClientService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findComClientByCode(String codeComClient) {
        return ResponseEntity.ok(commandeClientService.findComClientByCode(codeComClient));
    }

    @Override
    public ResponseEntity<List<LigneCommandeClientDto>> findAllLignesCommandeClientByIdCommandeClient(Integer idCommande) {
        return ResponseEntity.ok(commandeClientService.findAllLineCommandeClientByIdCommandeClient(idCommande));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(commandeClientService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        commandeClientService.findById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity deleteArticle(Integer idCommande, Integer idLigneCommande) {
        return ResponseEntity.ok(commandeClientService.deleteArticle(idCommande, idLigneCommande));
    }
}

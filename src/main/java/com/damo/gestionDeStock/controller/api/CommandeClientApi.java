package com.damo.gestionDeStock.controller.api;


import com.damo.gestionDeStock.dto.CommandeClientDto;
import com.damo.gestionDeStock.dto.LigneCommandeClientDto;
import com.damo.gestionDeStock.model.EtatCommande;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.damo.gestionDeStock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/commandeClients")
public interface CommandeClientApi {

    @PostMapping(APP_ROOT + "/commandeClients/create")
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto cmdCltDto);

    @PatchMapping(APP_ROOT + "/commandeClients/etat/update/{idCommande}/{etatCommande}")
    ResponseEntity<CommandeClientDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande")EtatCommande etatCommande);

    @PatchMapping(APP_ROOT + "/commandeClients/client/update/{idCommande}/{idClient}")
    ResponseEntity<CommandeClientDto> updateClient(@PathVariable("idCommande") Integer idCommande, @PathVariable("idClient") Integer idClient);

    @PatchMapping(APP_ROOT + "/commandeClients/quantite/update/{idCommande}/{idLigneCommande}/{quantite}")
    ResponseEntity<CommandeClientDto> updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande,
                                                             @PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(APP_ROOT + "/commandeClients/article/update/{idCommande}/{idLigneCommande}/{idArticle}")
    ResponseEntity<CommandeClientDto> updateArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);

    @GetMapping(APP_ROOT + "/commandeClients/{idCommandeClient}")
    ResponseEntity<CommandeClientDto> findById(@PathVariable("idCommandeClient") Integer idCommandeClient);

    @GetMapping(APP_ROOT + "/commandeClients/codeCOMCLT/{codeCommandeClient}")
    ResponseEntity<CommandeClientDto> findComClientByCode(@PathVariable("codeCommandeClient") String codeComClient);

    @GetMapping(APP_ROOT + "/commandeClients/ligneCommande/{idCommande}")
    ResponseEntity<List<LigneCommandeClientDto>> findAllCommandeClientByIdCommandeClient(@PathVariable("idCommande") Integer idCommande);

    @GetMapping(APP_ROOT + "/commandeClients/all")
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(APP_ROOT + "/commandeClients/delete/{idCommandeClient}")
    ResponseEntity<Void> delete(@PathVariable("idCommandeClient") Integer id);

    @DeleteMapping(APP_ROOT + "/commandeClients/deleteArticle/{idCommande}/{idLigneCommande}")
    ResponseEntity<Void> deleteArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);
}

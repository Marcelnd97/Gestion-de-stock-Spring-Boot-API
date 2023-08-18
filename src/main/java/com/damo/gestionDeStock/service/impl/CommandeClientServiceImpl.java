package com.damo.gestionDeStock.service.impl;

import com.damo.gestionDeStock.dto.*;
import com.damo.gestionDeStock.handlers.exception.EntityNotFoundException;
import com.damo.gestionDeStock.handlers.exception.ErrorCodes;
import com.damo.gestionDeStock.handlers.exception.InvalidEntityException;
import com.damo.gestionDeStock.handlers.exception.InvalideOperationException;
import com.damo.gestionDeStock.model.*;
import com.damo.gestionDeStock.repository.ArticleRepository;
import com.damo.gestionDeStock.repository.ClientRepository;
import com.damo.gestionDeStock.repository.CommandeClientRepository;
import com.damo.gestionDeStock.repository.LigneCommandeClientRepository;
import com.damo.gestionDeStock.service.CommandeClientService;
import com.damo.gestionDeStock.service.MouveStkService;
import com.damo.gestionDeStock.validator.ArticleValidator;
import com.damo.gestionDeStock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {


    private final CommandeClientRepository commandeClientRepository;

    private final ClientRepository clientRepository;

    private final ArticleRepository articleRepository;


    private final LigneCommandeClientRepository ligneCommandeClientRepository;

    private final MouveStkService mouveStkService;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
                                     ClientRepository clientRepository,
                                     ArticleRepository articleRepository,
                                     LigneCommandeClientRepository ligneCommandeClientRepository,
                                     MouveStkService mouveStkService) {

        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.mouveStkService = mouveStkService;
    }


    @Override
    public CommandeClientDto save(CommandeClientDto cmdCltDto) {

        List<String> errors = CommandeClientValidator.validator(cmdCltDto);

        if (!errors.isEmpty()) {
            log.error("Commande client n'est pas valide");
            throw new InvalidEntityException("La commande client n'est pas valide",
                    ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        if (cmdCltDto.getId() != null && cmdCltDto.isCommandeLivree()) {
            throw new InvalideOperationException(
                    "Impossible de modifier la commande lorsqu'elle est livree",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }

        Optional<Client> client = clientRepository.findById(cmdCltDto.getClient().getId());
        if (client.isEmpty()) {
            log.warn("Client with ID {} was not found in the DB", cmdCltDto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID" + cmdCltDto.getClient()
                    .getId() + " n'a ete trouve dans la BDD",
                    ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (cmdCltDto.getLigneCommandeClients() != null) {
            cmdCltDto.getLigneCommandeClients().forEach(ligCmdClt -> {
                if (ligCmdClt.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID " + ligCmdClt.getArticle()
                                .getId() + " n'existe pas");
                    }
                } else {
                    articleErrors.add("Impossible d'enregister une commande avec un aticle NULL");
                }
            });
        }



        //Ici ça sera un teste bloquant si un article n'existe pas dans la bdd on doit refuser l'accès.

        if (!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("l'article n'existe pas dans la BDD",
                    ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }
        //Ici c'est si les commande client sont valide
        //On enregistre les objets dans notre bdd

        cmdCltDto.setDateCommande(Instant.now());
        CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto
                .toEntity(cmdCltDto));

        if (cmdCltDto.getLigneCommandeClients() != null) {
         cmdCltDto.getLigneCommandeClients().forEach(ligCmdClt -> {
            LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
            ligneCommandeClient.setCommandeClient(savedCmdClt);
            ligneCommandeClient.setIdEntreprise(cmdCltDto.getIdEntreprise());
            LigneCommandeClient savedLigneCmd = ligneCommandeClientRepository.save(ligneCommandeClient);

            effectuerSortie(savedLigneCmd);
        });
        }
        return  CommandeClientDto.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        //Refactoring
        checkIdCommande(idCommande);

        if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
            log.error("L'etat de la commande est null");
            throw new InvalideOperationException(
                    "Impossible de modifier l'etat de la commande avec un ID null",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
        CommandeClientDto commandeClient = checkEtatCommande(idCommande);
        commandeClient.setEtatCommande(etatCommande);

        CommandeClient saveCmdClt = commandeClientRepository.save(CommandeClientDto
                .toEntity(commandeClient));
        if (commandeClient.isCommandeLivree()) {
            updateMvtStk(idCommande);
        }


        return CommandeClientDto.fromEntity(saveCmdClt);
    }

    @Override
    public CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande,
                                                    BigDecimal quantity) {

        //Refactoring
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);

        if (quantity == null || quantity.compareTo(BigDecimal.ZERO) == 0) {
            log.error("L'ID de la ligne commande est null");
            throw new InvalideOperationException(
                    "Impossible de modifier l'etat de la commande avec une quantité null ou ZERO",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
        //Refactoring

        CommandeClientDto commandeClient = checkEtatCommande(idCommande);

        Optional<LigneCommandeClient> ligneCommandeClientOptional = findLigneCommandeClient(idLigneCommande);

        LigneCommandeClient ligneCommandeClient1 = ligneCommandeClientOptional.get();
        ligneCommandeClient1.setQuantite(quantity);
        ligneCommandeClientRepository.save(ligneCommandeClient1);
        return commandeClient;
    }
    @Override
    public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {

        //Refactoring
        checkIdCommande(idCommande);

        if (idClient == null) {
            log.error("L'ID du client est null");
            throw new InvalideOperationException(
                    "Impossible de modifier l'etat de la commande avec l'ID client null",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }

        //Refactoring
        CommandeClientDto commandeClient = checkEtatCommande(idCommande);

        Optional<Client> clientOptional = clientRepository.findById(idClient);
        if (clientOptional.isEmpty()){
            throw new EntityNotFoundException("Aucun client n'a été trouvé avec l'ID " + idClient,
                    ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
        }
        commandeClient.setClient(ClientDto.fromEntity(clientOptional.get()));

        return CommandeClientDto.fromEntity(
                commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient))
        );
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (id == null){
            log.error("Commande client ID is null.");
            return null;
        }
        return commandeClientRepository.findById(id)
              .map(CommandeClientDto::fromEntity).orElseThrow(() ->
               new EntityNotFoundException("Aucune commande client n'a ete retrouvé avec l'ID" + id,
                   ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeClientDto findComClientByCode(String codeComClient) {
        if (!StringUtils.hasLength(codeComClient)){
            log.error("Commande client CODE is null.");
            return null;
        }


        Optional<CommandeClient> commandeClient = commandeClientRepository.findComClientByCode(codeComClient);

        return Optional.of(CommandeClientDto.fromEntity(commandeClient.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun article avec le CODE ="
                        + codeComClient + "n'été trouver dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND));

    }

    @Override
    public List<LigneCommandeClientDto> findAllLineCommandeClientByIdCommandeClient(Integer idCommande) {
        return ligneCommandeClientRepository.findAllByCommandeClientId(idCommande).stream()
                .map(LigneCommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Commande client ID is null");
        }
        List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository
                .findAllByCommandeClientId(id);
        if (!ligneCommandeClients.isEmpty()) {
            throw new InvalideOperationException("Impossible de supprimer une commande client deja utilisee",
                    ErrorCodes.COMMANDE_CLIENT_ALREADY_IN_USE);
        }
        commandeClientRepository.deleteById(id);
    }

    @Override
    public CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande) {

        //Refactoring
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        CommandeClientDto commandeClient = checkEtatCommande(idCommande);

        //Rechercher just la ligneCommandeClient et inormer le client dans le cas où il est absent.
        findLigneCommandeClient(idLigneCommande);
        ligneCommandeClientRepository.deleteById(idLigneCommande);
        return commandeClient;
    }

    @Override
    public CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {

        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        checkIdArticle(idArticle, "nouvel");

        CommandeClientDto commandeClient = checkEtatCommande(idCommande);

        Optional<LigneCommandeClient> ligneCommandeClient = findLigneCommandeClient(idLigneCommande);

        Optional<Article>  articleOptional = articleRepository.findById(idArticle);
        if (articleOptional.isEmpty()){
            throw new EntityNotFoundException("Aucun article n'a été trouvé avec l'ID " + idArticle,
                    ErrorCodes.ARTICLE_NOT_FOUND);
        }
        List<String> errors = ArticleValidator.validator(ArticleDto.fromEntity(articleOptional.get()));
        if (!errors.isEmpty()){
            throw new InvalidEntityException("Article invalide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }

        LigneCommandeClient ligneCommandeClientToSave = ligneCommandeClient.get();
        ligneCommandeClientToSave.setArticle(articleOptional.get());
        ligneCommandeClientRepository.save(ligneCommandeClientToSave);

        return commandeClient;
    }
    //Refactoring

    private CommandeClientDto checkEtatCommande(Integer idCommande) {

        CommandeClientDto commandeClient = findById(idCommande);

        if (commandeClient.isCommandeLivree()) {
            throw new InvalideOperationException("Impossible de modifier la commande lorsqu'elle est livrée.",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
        return commandeClient;
    }

    private void checkIdCommande(Integer idCommande){

        if (idCommande == null) {
            log.error("CommandeClient ID is NULL");
            throw new InvalideOperationException(
                    "Impossible de modifier l'etat de la commande avec un ID null",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
    }

    private void checkIdLigneCommande(Integer idLigneCommande) {

        if (idLigneCommande == null) {
            log.error("L'ID de la ligne commande est null");
            throw new InvalideOperationException(
                    "Impossible de modifier l'etat de la commande avec une ligne de commande null",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
    }

    private void checkIdArticle(Integer idArticle, String msg)  {

        if (idArticle == null) {
            log.error("L'ID de " + msg + "article est null");
            throw new InvalideOperationException("Impossible de modifier l'etat de la commande avec un "
                    + msg + "ID article null",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
    }

    private Optional<LigneCommandeClient> findLigneCommandeClient(Integer idLigneCommande) {
        Optional<LigneCommandeClient> ligneCommandeClientOptional =
                ligneCommandeClientRepository.findById(idLigneCommande);
        if (ligneCommandeClientOptional.isEmpty()){
            throw new EntityNotFoundException("Aucune commande client n'a été trouvé avec l'ID "
                    + idLigneCommande,
                    ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
        }

        return ligneCommandeClientOptional;
    }

    private void updateMvtStk(Integer idCommande){
        List<LigneCommandeClient> ligneCommandeClients =
                ligneCommandeClientRepository.findAllByCommandeClientId(idCommande);
        ligneCommandeClients.forEach(lig ->{
            effectuerSortie(lig);
        });
    }

    private void effectuerSortie(LigneCommandeClient lig) {
        MouveStockDto mouveStk = MouveStockDto.builder()
                .article(ArticleDto.fromEntity(lig.getArticle()))
                .dateMouveStock(Instant.now())
                .typeMouveStk(TypeMouveStk.SORTIE)
                .sourceMvt(SourceMvt.COMMANDE_CLIENT)
                .quantite(lig.getQuantite())
                .idEntreprise((lig.getIdEntreprise()))
                .build();

        mouveStkService.sortieStock(mouveStk);
    }
}

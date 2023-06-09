package com.damo.gestionDeStock.service.impl;

import com.damo.gestionDeStock.dto.ArticleDto;
import com.damo.gestionDeStock.dto.ClientDto;
import com.damo.gestionDeStock.dto.CommandeClientDto;
import com.damo.gestionDeStock.dto.LigneCommandeClientDto;
import com.damo.gestionDeStock.exception.EntityNotFoundException;
import com.damo.gestionDeStock.exception.ErrorCodes;
import com.damo.gestionDeStock.exception.InvalidEntityException;
import com.damo.gestionDeStock.exception.InvalideOperationException;
import com.damo.gestionDeStock.model.*;
import com.damo.gestionDeStock.repository.ArticleRepository;
import com.damo.gestionDeStock.repository.ClientRepository;
import com.damo.gestionDeStock.repository.CommandeClientRepository;
import com.damo.gestionDeStock.repository.LigneCommandeClientRepository;
import com.damo.gestionDeStock.service.CommandeClientService;
import com.damo.gestionDeStock.validator.ArticleValidator;
import com.damo.gestionDeStock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {


    private CommandeClientRepository commandeClientRepository;

    private final ClientRepository clientRepository;

    private final ArticleRepository articleRepository;


    private final LigneCommandeClientRepository ligneCommandeClientRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
                                     ClientRepository clientRepository,
                                     ArticleRepository articleRepository,
                                     LigneCommandeClientRepository ligneCommandeClientRepository) {

        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto cmdCltDto) {

        //validation de premiere niveau;

        List<String> errors = CommandeClientValidator.validator(cmdCltDto);

        if (!errors.isEmpty()){
            log.error("CommandeClient is not valid.");
            throw new InvalidEntityException("La commande du client Client n'est pas valide",
                        ErrorCodes.COMMANDE_CLIENT_NOT_FOUND, errors);
        }
        //Teste pour la validation d'une commande client
        //etat de modification de la commande

        if (cmdCltDto.getId() != null && cmdCltDto.isCommandeLivree()){
            throw new InvalideOperationException("Impossible de modifier la commande lorsqu'elle est livrée", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }

        // Si j'arrive à ce niveau, je suis sur que j'ai toutes les informations;
        //Ensuite je fait une validation métier

        Optional<Client> client = clientRepository.findById(cmdCltDto.getClient().getId());
        if (client.isEmpty()){
            log.warn("Client with ID {} is not valid" + cmdCltDto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID " + cmdCltDto.getClient().getId() + "n'a été trouvé dans la BDD",
                    ErrorCodes.CLIENT_NOT_FOUND);
        }

        //Verification des ligne de commandeClient
        //Ici on doit verifier que les articles ou les Id des Articles existent dans ma BDD;

        List<String> articleErrors = new ArrayList<>();

        if (cmdCltDto.getLigneCommandeClients() != null){

            cmdCltDto.getLigneCommandeClients().forEach(ligCmdClt -> {

                //Je dois verifie que l'article n'est pas null;

                if (ligCmdClt.getCommandeClient() != null){
                   Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());

                   //Ici on essaie de chercher l'article par son ID

                    if (articleErrors.isEmpty()){
                        articleErrors.add("L'article avec l'ID" + ligCmdClt.getArticle().getId() + "n'existe pas.");
                    }
                    else {
                        articleErrors.add("Impossible d'enregistrer un article avec un article null.");
                    }
                }
            });
        }
        //Ici ça sera un teste bloquant si un article n'existe pas dans la bdd on doit refuser l'accès.

        if (!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("l'article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }
        //Ici c'est si les commande client sont valide
        //On enregistre les objets dans notre bdd

        CommandeClient saveCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(cmdCltDto));

        cmdCltDto.getLigneCommandeClients().forEach(ligCmdClt ->{
            LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
            ligneCommandeClient.setCommandeClient(saveCmdClt);
            ligneCommandeClientRepository.save(ligneCommandeClient);
        });
        return  CommandeClientDto.fromEntity(saveCmdClt);
    }

    @Override
    public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        //Refactoring
        checkIdCommande(idCommande);

        if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
            log.error("L'etat de la commande est null");
            throw new InvalideOperationException("Impossible de modifier l'etat de la commande avec un ID null", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
        CommandeClientDto commandeClient = findById(idCommande);

        if (commandeClient.isCommandeLivree()) {
            throw new InvalideOperationException("Impossible de modifier la commande lorsqu'elle est livrée.", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }

        commandeClient.setEtatCommande(etatCommande);

        return CommandeClientDto.fromEntity(
                 commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient)
        ));
    }

    @Override
    public CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantity) {

        //Refactoring
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);

        if (quantity == null || quantity.compareTo(BigDecimal.ZERO) == 0) {
            log.error("L'ID de la ligne commande est null");
            throw new InvalideOperationException("Impossible de modifier l'etat de la commande avec une quantité null ou ZERO",
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
            throw new InvalideOperationException("Impossible de modifier l'etat de la commande avec l'ID client null",
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
                        new EntityNotFoundException("Aucune commande client n'a ete retrouvé avec l'ID" + id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeClientDto findComClientByCode(String codeComClient) {
        if (codeComClient == null){
            log.error("Commande client CODE is null.");
            return null;
        }


        Optional<CommandeClient> commandeClient = commandeClientRepository.findComClientByCode(codeComClient);

        return Optional.of(CommandeClientDto.fromEntity(commandeClient.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun article avec le CODE =" + codeComClient + "n'été trouver dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND));

    }

    @Override
    public List<LigneCommandeClientDto> findAllLineCommandeClientByIdCommandeClient(Integer idCommande) {
        return ligneCommandeClientRepository.findAllLByCommandeClientId(idCommande).stream()
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
            throw new InvalideOperationException("Impossible de modifier la commande lorsqu'elle est livrée.", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
        return commandeClient;
    }

    private void checkIdCommande(Integer idCommande){

        if (idCommande == null) {
            log.error("CommandeClient ID is NULL");
            throw new InvalideOperationException("Impossible de modifier l'etat de la commande avec un ID null",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
    }

    private void checkIdLigneCommande(Integer idLigneCommande) {

        if (idLigneCommande == null) {
            log.error("L'ID de la ligne commande est null");
            throw new InvalideOperationException("Impossible de modifier l'etat de la commande avec une ligne de commande null",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
    }

    private void checkIdArticle(Integer idArticle, String msg)  {

        if (idArticle == null) {
            log.error("L'ID de " + msg + "article est null");
            throw new InvalideOperationException("Impossible de modifier l'etat de la commande avec un " + msg + "ID article null",
                    ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
    }

    private Optional<LigneCommandeClient> findLigneCommandeClient(Integer idLigneCommande) {

        Optional<LigneCommandeClient> ligneCommandeClientOptional = ligneCommandeClientRepository.findById(idLigneCommande);
        if (ligneCommandeClientOptional.isEmpty()){
            throw new EntityNotFoundException("Aucune commande client n'a été trouvé avec l'ID " + idLigneCommande,
                    ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
        }

        return ligneCommandeClientOptional;
    }
}

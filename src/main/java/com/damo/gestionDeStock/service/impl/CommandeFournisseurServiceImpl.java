package com.damo.gestionDeStock.service.impl;

import com.damo.gestionDeStock.dto.*;
import com.damo.gestionDeStock.dto.CommandeFournisseurDto;
import com.damo.gestionDeStock.dto.LigneCommandeFournisseurDto;
import com.damo.gestionDeStock.handlers.exception.EntityNotFoundException;
import com.damo.gestionDeStock.handlers.exception.ErrorCodes;
import com.damo.gestionDeStock.handlers.exception.InvalidEntityException;
import com.damo.gestionDeStock.handlers.exception.InvalideOperationException;
import com.damo.gestionDeStock.model.*;
import com.damo.gestionDeStock.repository.*;
import com.damo.gestionDeStock.service.CommandeFournisseurService;
import com.damo.gestionDeStock.service.MouveStkService;
import com.damo.gestionDeStock.validator.ArticleValidator;
import com.damo.gestionDeStock.validator.CommandeFournisseurValidator;
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
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {


    private final CommandeFournisseurRepository commandeFournisseurRepository;

    private final FournisseurRepository fournisseurRepository;

    private final ArticleRepository articleRepository;
    private final MouveStkService mouveStkService;

    private final LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
                                          FournisseurRepository fournisseurRepository,
                                          ArticleRepository articleRepository,
                                          MouveStkService mouveStkService, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository) {

        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
        this.mouveStkService = mouveStkService;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto cmdFrsDto) {

        //validation de premiere niveau;

        List<String> errors = CommandeFournisseurValidator.validator(cmdFrsDto);

        if (!errors.isEmpty()){
            log.error("Commande Fournisseur is not valid.");
            throw new InvalidEntityException("La commande du fournisseur n'est pas valide",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }
        // Si j'arrive à ce niveau, je suis sur que j'ai toutes les informations;
        //Ensuite je fait une validation métier

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(cmdFrsDto.getFournisseur().getId());
        if (fournisseur.isEmpty()){
            log.warn("Fournisseur with ID {} was not found in the DB", cmdFrsDto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun fournisseur avec l'ID " + cmdFrsDto.getFournisseur().getId() + "n'a été trouvé dans la BDD",
                    ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        //Verification des ligne de commandeClient
        //Ici on doit verifier que les articles ou les Id des Articles existent dans ma BDD;

        List<String> articleErrors = new ArrayList<>();

        if (cmdFrsDto.getLignecommandeFournisseur() != null){

            cmdFrsDto.getLignecommandeFournisseur().forEach(ligCmdClt -> {

                //Je dois verifie que l'article n'est pas null;

                if (ligCmdClt.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());

                    //Ici on essaie de chercher l'article par son ID

                    if (article.isEmpty()){
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

        cmdFrsDto.setDateCommande(Instant.now());
        CommandeFournisseur saveCmdFrs = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(cmdFrsDto));
        if (cmdFrsDto.getLignecommandeFournisseur() != null) {

         cmdFrsDto.getLignecommandeFournisseur().forEach(ligCmdFrs ->{
            LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligCmdFrs);
            ligneCommandeFournisseur.setCommandeFournisseur(saveCmdFrs);
            ligneCommandeFournisseur.setIdEntreprise(saveCmdFrs.getIdEntreprise());
            LigneCommandeFournisseur saveLigne = ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);

            effectuerEntree(saveLigne);
         });
        }
        return  CommandeFournisseurDto.fromEntity(saveCmdFrs);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {

        if (id == null){
            log.error("Commande fournisseur ID is null.");
            return null;
        }

        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity).orElseThrow(() ->
                        new EntityNotFoundException("Aucune commande fournisseur n'a ete retrouvé avec l'ID" + id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeFournisseurDto findComFrsByCode(String codeComFrs) {
        if (codeComFrs == null){
            log.error("Commande fournisseur CODE is null.");
            return null;
        }


        Optional<CommandeFournisseur> commandeFournisseur = commandeFournisseurRepository.findComFrsByCode(codeComFrs);

        return Optional.of(CommandeFournisseurDto.fromEntity(commandeFournisseur.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun article avec le CODE =" + codeComFrs + "n'été trouver dans la BDD",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND));

    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            log.error("Commande fournisseur ID is NULL");
            return;
        }

        List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFournisseurRepository.findAllCommandeFournisseurById(id);
        if (!ligneCommandeFournisseurs.isEmpty()) {
            throw new InvalideOperationException("Impossible de supprimer une commande client deja utilisee",
                    ErrorCodes.COMMANDE_CLIENT_ALREADY_IN_USE);
        }
        commandeFournisseurRepository.deleteById(id);
    }


    @Override
    public CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        //Refactoring
        checkIdCommande(idCommande);

        if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
            log.error("L'etat de la commande fournisseur est null");
            throw new InvalideOperationException("Impossible de modifier l'etat de la commande avec un ID null", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
        }
        CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);
        commandeFournisseur.setEtatCommande(etatCommande);

        CommandeFournisseur savedCommande = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseur));
        if (commandeFournisseur.isCommandeLivree()) {
            updateMvtStk(idCommande);
        }


        return CommandeFournisseurDto.fromEntity(savedCommande);
    }

    @Override
    public CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantity) {
        //Refactoring
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);

        if (quantity == null || quantity.compareTo(BigDecimal.ZERO) == 0) {
            log.error("L'ID de la ligne commande est null");
            throw new InvalideOperationException("Impossible de modifier l'etat de la commande avec une quantité null ou ZERO",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
        }
        //Refactoring

        CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);

        Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional = findLigneCommandeFournisseur(idLigneCommande);

        LigneCommandeFournisseur ligneCommandeFournisseur1 = ligneCommandeFournisseurOptional.get();
        ligneCommandeFournisseur1.setQuantite(quantity);
        ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur1);
        return commandeFournisseur;
    }
    

    @Override
    public CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        //Refactoring

        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);

        //Rechercher just la ligneCommandeFournisseur et inormer le client dans le cas où il est absent.

        findLigneCommandeFournisseur(idLigneCommande);
        ligneCommandeFournisseurRepository.deleteById(idLigneCommande);
        return commandeFournisseur;
    }

    @Override
    public CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        checkIdArticle(idArticle, "nouvel");

        CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);

        Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional = findLigneCommandeFournisseur(idLigneCommande);

        Optional<Article>  articleOptional = articleRepository.findById(idArticle);
        if (articleOptional.isEmpty()){
            throw new EntityNotFoundException("Aucun article n'a été trouvé avec l'ID " + idArticle,
                    ErrorCodes.ARTICLE_NOT_FOUND);
        }
        List<String> errors = ArticleValidator.validator(ArticleDto.fromEntity(articleOptional.get()));
        if (!errors.isEmpty()){
            throw new InvalidEntityException("Article invalide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }

        LigneCommandeFournisseur ligneCommandeFournisseurToSave = ligneCommandeFournisseurOptional.get();
        ligneCommandeFournisseurToSave.setArticle(articleOptional.get());
        ligneCommandeFournisseurRepository.save(ligneCommandeFournisseurToSave);

        return commandeFournisseur;
    }

    @Override
    public CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur) {

            //Refactoring
            checkIdCommande(idCommande);

            if (idFournisseur == null) {
                log.error("L'ID du client est null");
                throw new InvalideOperationException("Impossible de modifier l'etat de la commande avec l'ID fournisseur null",
                        ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
            }

            //Refactoring
            CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);

            Optional<Fournisseur> fournisseurOptional = fournisseurRepository.findById(idFournisseur);
            if (fournisseurOptional.isEmpty()){
                throw new EntityNotFoundException("Aucun client n'a été trouvé avec l'ID " + idFournisseur,
                        ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
            }
        commandeFournisseur.setFournisseur(FournisseurDto.fromEntity(fournisseurOptional.get()));

            return CommandeFournisseurDto.fromEntity(
                    commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseur))
            );
        }

    @Override
    public List<LigneCommandeFournisseurDto> findAllLineCommandeFournisseurByIdCommande(Integer idCommande) {
        return ligneCommandeFournisseurRepository.findAllCommandeFournisseurById(idCommande).stream()
                .map(LigneCommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }


    private CommandeFournisseurDto checkEtatCommande(Integer idCommande) {

        CommandeFournisseurDto commandeFournisseur = findById(idCommande);

        if (commandeFournisseur.isCommandeLivree()) {
            throw new InvalideOperationException("Impossible de modifier la commande lorsqu'elle est livrée.",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
        }
        return commandeFournisseur;
    }
    private void checkIdLigneCommande(Integer idLigneCommande) {

        if (idLigneCommande == null) {
            log.error("L'ID de la ligne commande est null");
            throw new InvalideOperationException("Impossible de modifier l'etat de la commande avec une ligne de commande null",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
        }
    }
    private void checkIdCommande(Integer idCommande){

        if (idCommande == null) {
            log.error("CommandeFournisseur ID is NULL");
            throw new InvalideOperationException("Impossible de modifier l'etat de la commande avec un ID null",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
        }
    }

    private Optional<LigneCommandeFournisseur> findLigneCommandeFournisseur(Integer idLigneCommande) {

        Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional = ligneCommandeFournisseurRepository.findById(idLigneCommande);
        if (ligneCommandeFournisseurOptional.isEmpty()){
            throw new EntityNotFoundException("Aucune commande fournisseur n'a été trouvé avec l'ID " + idLigneCommande,
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
        }

        return ligneCommandeFournisseurOptional;
    }

    private void checkIdArticle(Integer idArticle, String msg)  {

        if (idArticle == null) {
            log.error("L'ID de " + msg + "article est null");
            throw new InvalideOperationException("Impossible de modifier l'etat de la commande avec un " + msg + "ID fournisseur null",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
        }
    }

    private void updateMvtStk(Integer idCommande) {
        List<LigneCommandeFournisseur> ligneCommandeFournisseur = ligneCommandeFournisseurRepository.findAllCommandeFournisseurById(idCommande);
        ligneCommandeFournisseur.forEach(lig -> {
            effectuerEntree(lig);
        });
    }

    private void effectuerEntree(LigneCommandeFournisseur lig) {
        MouveStockDto mouveStockDto = MouveStockDto.builder()
                .article(ArticleDto.fromEntity(lig.getArticle()))
                .dateMouveStock(Instant.now())
                .typeMouveStk(TypeMouveStk.ENTREE)
                .sourceMvt(SourceMvt.COMMANDE_FOURNISSEUR)
                .quantite(lig.getQuantite())
                .idEntreprise(lig.getIdEntreprise())
                .build();
        mouveStkService.entreeStock(mouveStockDto);
    }
}

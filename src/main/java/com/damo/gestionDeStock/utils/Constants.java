package com.damo.gestionDeStock.utils;

public interface Constants {

    String APP_ROOT = "gestiondestock/v1";

    String COMMANDE_FOURNISSEUR_ENDPOINT = APP_ROOT + "/commandesFournisseur";
    String CREATE_COMMANDEFOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/create";
    String FIND_BY_ID_COMMANDEFOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/{idCommandeFournisseur}";
    String FIND_BY_CODE_COMMANDEFOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/codeCOMF/{codeCommandeFournisseur}";
    String FIND_ALL_COMMANDEFOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/all";
    String DELETE_BY_ID_COMMANDEFOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/delete/{idCommandeFournisseur}";

    String UPDATE_ETAT_COMMANDE_BY_ID_COMMANDE_AND_ETAT_COMMANDE_COMMANDE_FOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/etat/update/{idCommande}/{etatCommande}";

    String UPDATE_FOURNISSEUR_BY_ID_COMMANDE_AND_ID_FOURNISSEUR_COMMANDE_FOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/fournisseur/update/{idCommande}/{idFournisseur}";

    String UPDATE_QUANTITY_COMMANDE_FOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT +"/quantite/update/{idCommande}/{idLigneCommande}/{quantite}";

    String UPDATE_ARTICLE_COMMANDE_FOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT +"/article/update/{idCommande}/{idLigneCommande}/{idArticle}";

    String FIND_ALL_BY_ID_COMMANDEFOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT +"/ligneCommande/{idCommande}";

    String DELETE_ARTICLE_COMMANDEFOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT +"/deleteArticle/{idCommande}/{idLigneCommande}";
    String AUTHENTICATION_ENDPOINT = APP_ROOT + "/auth";

}

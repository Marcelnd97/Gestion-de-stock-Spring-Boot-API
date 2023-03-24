package com.damo.gestionDeStock.service;

import com.damo.gestionDeStock.dto.ChangerMotDePasseUtilisateurDto;
import com.damo.gestionDeStock.dto.UtilisateurDto;
import com.damo.gestionDeStock.model.Roles;
import com.damo.gestionDeStock.model.Utilisateur;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto utilisDto);
    UtilisateurDto findById(Integer id);
    List<UtilisateurDto> findAll();
    void delete(Integer id);
    UtilisateurDto findByMail(String mail);
    UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto changerMotDePasseDto);
}

package com.damo.gestionDeStock.service;

import com.damo.gestionDeStock.dto.ChangerMotDePasseUtilisateurDto;
import com.damo.gestionDeStock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto utilisDto);
    UtilisateurDto findById(Integer id);
    List<UtilisateurDto> findAll();
    void delete(Integer id);
    UtilisateurDto findByMail(String mail);
    UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto CMDPDto);
}

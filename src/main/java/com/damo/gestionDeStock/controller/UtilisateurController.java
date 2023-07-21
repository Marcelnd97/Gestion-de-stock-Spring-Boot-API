package com.damo.gestionDeStock.controller;

import com.damo.gestionDeStock.controller.api.UtilisateurApi;
import com.damo.gestionDeStock.dto.ChangerMotDePasseUtilisateurDto;
import com.damo.gestionDeStock.dto.UtilisateurDto;
import com.damo.gestionDeStock.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurApi {


    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService){
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisDto) {
        return utilisateurService.save(utilisDto);
    }

    @Override
    public UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto CMDPDto) {
        return utilisateurService.changerMotDePasse(CMDPDto);
    }


    @Override
    public UtilisateurDto findById(Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public UtilisateurDto findByMail(String mail) {
        return utilisateurService.findByMail(mail);
    }


    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        utilisateurService.delete(id);
    }
}

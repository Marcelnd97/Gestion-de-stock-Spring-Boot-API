package com.damo.gestionDeStock.controller.api;

import com.damo.gestionDeStock.dto.ChangerMotDePasseUtilisateurDto;
import com.damo.gestionDeStock.dto.UtilisateurDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.damo.gestionDeStock.utils.Constants.APP_ROOT;
import static com.damo.gestionDeStock.utils.Constants.UTILISATEUR_ENDPOINT;

@Api("utilisateur")
public interface UtilisateurApi {

    @PostMapping(UTILISATEUR_ENDPOINT + "/create")
    UtilisateurDto save(@RequestBody UtilisateurDto utilisDto);

    @PostMapping(UTILISATEUR_ENDPOINT + "/update/password")
    UtilisateurDto changerMotDePasse(@RequestBody ChangerMotDePasseUtilisateurDto CMDPDto);

    @GetMapping(UTILISATEUR_ENDPOINT + "/{idUtilisateur}")
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(UTILISATEUR_ENDPOINT + "/find/{mail}")
    UtilisateurDto findByMail(@PathVariable("mail") String mail);

    @GetMapping(UTILISATEUR_ENDPOINT + "/all")
    List<UtilisateurDto> findAll();

    @DeleteMapping(UTILISATEUR_ENDPOINT + "/delete/{idUtilisateur}")
    void delete(@PathVariable("idUtilisateur") Integer id);
}

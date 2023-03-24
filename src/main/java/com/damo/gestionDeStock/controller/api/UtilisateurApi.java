package com.damo.gestionDeStock.controller.api;

import com.damo.gestionDeStock.dto.UtilisateurDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.damo.gestionDeStock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/utilisateur")
public interface UtilisateurApi {

    @PostMapping(value = APP_ROOT + "/utilisateur/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(@RequestBody UtilisateurDto utilisDto);

    @GetMapping(value = APP_ROOT + "/utilisateeur/{idUtilisateur}", produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(value = APP_ROOT + "/utilisateur/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/utilisateur/delete/{idUtilisateur}")
    void delete(@PathVariable("idUtilisateur") Integer id);
}

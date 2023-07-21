package com.damo.gestionDeStock.controller.api;

import com.damo.gestionDeStock.dto.EntrepriseDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.damo.gestionDeStock.utils.Constants.APP_ROOT;
import static com.damo.gestionDeStock.utils.Constants.ENTREPRISE_ENDPOINT;

@Api("entreprise")
public interface EntrepriseApi {

    @PostMapping(value = ENTREPRISE_ENDPOINT + "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(@RequestBody EntrepriseDto entrepriseDto);

    @GetMapping(value = ENTREPRISE_ENDPOINT + "/{idEntreprise}", produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findById(@PathVariable("idEntreprise")Integer id);

    @GetMapping(value = ENTREPRISE_ENDPOINT + "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> findAll();

    @DeleteMapping(value = ENTREPRISE_ENDPOINT + "/delete/{idEntreprise}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable("idEntreprise") Integer id);
}

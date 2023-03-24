package com.damo.gestionDeStock.controller.api;

import com.damo.gestionDeStock.dto.FournisseurDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.damo.gestionDeStock.utils.Constants.APP_ROOT;
@Api(APP_ROOT + "/fournisseur")
public interface FournisseurApi {

    @PostMapping(value = APP_ROOT + "/fournisseur/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto save(@RequestBody FournisseurDto fournisDto);

    @GetMapping(value = APP_ROOT + "/fournisseur/{idFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findById(@PathVariable("idFournisseur")Integer id);

    @GetMapping(value = APP_ROOT + "/fournisseur/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/fournisseur/delete/{idFournisseurt}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable("idFournisseurt") Integer id);
}

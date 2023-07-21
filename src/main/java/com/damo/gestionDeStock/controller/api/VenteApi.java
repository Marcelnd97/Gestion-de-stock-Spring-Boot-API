package com.damo.gestionDeStock.controller.api;

import com.damo.gestionDeStock.dto.VenteDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.damo.gestionDeStock.utils.Constants.APP_ROOT;

@Api("vente")
public interface VenteApi {

    @PostMapping(value = APP_ROOT + "/vente/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    VenteDto save(@RequestBody VenteDto venteDto);

    @GetMapping(value = APP_ROOT + "/vente/{idvente}", produces = MediaType.APPLICATION_JSON_VALUE)
    VenteDto findById(@PathVariable("idvente") Integer id);

    @GetMapping(value = APP_ROOT + "/vente/codeV/{codeVente}", produces = MediaType.APPLICATION_JSON_VALUE)
    VenteDto findVenteByCode(@PathVariable("codeVente") String codeVente);

    @GetMapping(value = APP_ROOT + "/vente/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<VenteDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/vente/delete/{idvente}")
    void delete(@PathVariable("idvente") Integer id);
}

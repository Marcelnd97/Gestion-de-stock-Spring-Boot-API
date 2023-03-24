package com.damo.gestionDeStock.controller.api;

import com.damo.gestionDeStock.dto.MouveStockDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.damo.gestionDeStock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/mouveStock")
public interface MouveStkApi {

    @PostMapping(value = "/mouveStock/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MouveStockDto save(@RequestBody MouveStockDto mouveStk);

    @GetMapping(value = APP_ROOT + "/mouveStock/{idmouvestock}", produces = MediaType.APPLICATION_JSON_VALUE)
    MouveStockDto findById(@PathVariable("idmouvestock") Integer id);

    @GetMapping(value = APP_ROOT + "/mouveStock/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<MouveStockDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/mouveStock/delete/{idmouvestock}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable("idmouvestock") Integer id);
}

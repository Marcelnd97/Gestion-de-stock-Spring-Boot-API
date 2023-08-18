package com.damo.gestionDeStock.controller.api;

import com.damo.gestionDeStock.dto.CategoryDto;
import com.damo.gestionDeStock.dto.MouveStockDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.damo.gestionDeStock.utils.Constants.APP_ROOT;

@Api("mouveStock")
public interface MouveStkApi {

    @GetMapping(APP_ROOT + "/mouveStock/stockreel/{idArticle}")
    BigDecimal stockReelArticle(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(APP_ROOT + "/mouveStock/filter/article/{idArticle}")
    List<MouveStockDto> mvtStkArticle(@PathVariable("idArticle") Integer idArticle);

    @PostMapping(APP_ROOT + "/mouveStock/entree")
    MouveStockDto entreeStock(@RequestBody MouveStockDto dto);

    @PostMapping(APP_ROOT + "/mouveStock/sortie")
    MouveStockDto sortieStock(@RequestBody MouveStockDto dto);

    @PostMapping(APP_ROOT + "/mouveStock/correctionpos")
    MouveStockDto correctionStockPos(@RequestBody MouveStockDto dto);

    @PostMapping(APP_ROOT + "/mouveStock/correctionneg")
    MouveStockDto correctionStockNeg(@RequestBody MouveStockDto dto);

    @GetMapping(value = APP_ROOT + "/mouveStock/findAllMvstk")
    List<MouveStockDto> findAllMouveStock();

}

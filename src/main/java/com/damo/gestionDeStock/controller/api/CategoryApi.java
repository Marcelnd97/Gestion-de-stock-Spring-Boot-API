package com.damo.gestionDeStock.controller.api;

import com.damo.gestionDeStock.dto.CategoryDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.damo.gestionDeStock.utils.Constants.APP_ROOT;


import java.util.List;
@Api("category")
public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/category/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save(@RequestBody CategoryDto categDto);

    @GetMapping(value = APP_ROOT + "/category/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findById(@PathVariable("idCategory")Integer id);

    @GetMapping(value = APP_ROOT + "/category/codeCAT/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findByCodeCategory(@PathVariable("codeCategory") String codeCategory);

    @GetMapping(value = APP_ROOT + "/category/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/category/delete/{idCategory}")
    void delete(@PathVariable("idCategory") Integer id);
}

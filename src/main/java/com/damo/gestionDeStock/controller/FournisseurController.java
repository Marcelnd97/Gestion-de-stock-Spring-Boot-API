package com.damo.gestionDeStock.controller;

import com.damo.gestionDeStock.controller.api.FournisseurApi;
import com.damo.gestionDeStock.dto.FournisseurDto;
import com.damo.gestionDeStock.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FournisseurController implements FournisseurApi {


    private FournisseurService fournisseurService;

    @Autowired
    public FournisseurController(FournisseurService fournisseurService){
        this.fournisseurService = fournisseurService;
    }
    @Override
    public FournisseurDto save(FournisseurDto fournisDto) {
        return fournisseurService.save(fournisDto);
    }

    @Override
    public FournisseurDto findById(Integer id) {
        return fournisseurService.findById(id);
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        fournisseurService.delete(id);
    }
}

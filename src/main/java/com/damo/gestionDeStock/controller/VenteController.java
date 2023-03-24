package com.damo.gestionDeStock.controller;

import com.damo.gestionDeStock.controller.api.VenteApi;
import com.damo.gestionDeStock.dto.VenteDto;
import com.damo.gestionDeStock.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VenteController implements VenteApi {


    private VenteService venteService;

    @Autowired
    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    @Override
    public VenteDto save(VenteDto venteDto) {
        return venteService.save(venteDto);
    }

    @Override
    public VenteDto findById(Integer id) {
        return venteService.findById(id);
    }

    @Override
    public VenteDto findVenteByCode(String codeVente) {
        return venteService.findVenteByCode(codeVente);
    }

    @Override
    public List<VenteDto> findAll() {
        return venteService.findAll();
    }

    @Override
    public void delete(Integer id) {
        venteService.delete(id);
    }
}

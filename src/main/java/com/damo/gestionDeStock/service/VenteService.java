package com.damo.gestionDeStock.service;

import com.damo.gestionDeStock.dto.VenteDto;
import com.damo.gestionDeStock.dto.VenteDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenteService {

    VenteDto save(VenteDto venteDto);
    VenteDto findById(Integer id);
    VenteDto findVenteByCode(String codeVente);
    List<VenteDto> findAll();

    void delete(Integer id);
}

package com.damo.gestionDeStock.service;

import com.damo.gestionDeStock.dto.EntrepriseDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrepriseService {

    EntrepriseDto save(EntrepriseDto entrepriseDto);
    EntrepriseDto findById(Integer id);
    List<EntrepriseDto> findAll();
    void deleteById(Integer id);
}

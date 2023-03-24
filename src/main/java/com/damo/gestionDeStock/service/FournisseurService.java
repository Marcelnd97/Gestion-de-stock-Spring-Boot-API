package com.damo.gestionDeStock.service;

import com.damo.gestionDeStock.dto.FournisseurDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FournisseurService {

    FournisseurDto save(FournisseurDto fournisDto);
    FournisseurDto findById(Integer id);
    List<FournisseurDto> findAll();
    void delete(Integer id);
}

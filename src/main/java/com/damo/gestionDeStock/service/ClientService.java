package com.damo.gestionDeStock.service;

import com.damo.gestionDeStock.dto.CategoryDto;
import com.damo.gestionDeStock.dto.ClientDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientService {

    ClientDto save(ClientDto clientDto);
    ClientDto findById(Integer id);
    List<ClientDto> findAll();
    void delete(Integer id);
}

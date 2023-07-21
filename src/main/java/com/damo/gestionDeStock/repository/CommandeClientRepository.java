package com.damo.gestionDeStock.repository;

import com.damo.gestionDeStock.dto.CommandeClientDto;
import com.damo.gestionDeStock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {
    Optional<CommandeClient> findComClientByCode(String codeComClient);

    List<CommandeClient> findAllByClientId(Integer id);
}

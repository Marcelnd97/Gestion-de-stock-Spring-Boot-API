package com.damo.gestionDeStock.repository;

import com.damo.gestionDeStock.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenteRepository extends JpaRepository<Ventes, Integer> {
    Optional<Ventes> findVenteByCode(String codeVente);
}

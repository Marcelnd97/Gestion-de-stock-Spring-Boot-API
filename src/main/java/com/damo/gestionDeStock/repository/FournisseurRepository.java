package com.damo.gestionDeStock.repository;

import com.damo.gestionDeStock.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
}

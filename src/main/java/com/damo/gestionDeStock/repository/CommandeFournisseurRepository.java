package com.damo.gestionDeStock.repository;

import com.damo.gestionDeStock.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {
    Optional<CommandeFournisseur> findComFrsByCode(String codeComFrs);

    List<CommandeFournisseur> findAllByFournisseurId(Integer id);
}

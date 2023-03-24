package com.damo.gestionDeStock.repository;

import com.damo.gestionDeStock.model.MouveStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MouveStkRepository extends JpaRepository<MouveStock, Integer> {
}

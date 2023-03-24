package com.damo.gestionDeStock.repository;

import com.damo.gestionDeStock.dto.RolesDto;
import com.damo.gestionDeStock.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
}

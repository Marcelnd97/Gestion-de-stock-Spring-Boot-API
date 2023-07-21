package com.damo.gestionDeStock.repository;

import com.damo.gestionDeStock.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
}

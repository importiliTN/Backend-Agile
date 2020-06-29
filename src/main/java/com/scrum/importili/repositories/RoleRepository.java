package com.scrum.importili.repositories;

import com.scrum.importili.mvc.model.ERole;
import com.scrum.importili.mvc.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
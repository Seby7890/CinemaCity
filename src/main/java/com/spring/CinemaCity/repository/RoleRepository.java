package com.spring.CinemaCity.repository;

import com.spring.CinemaCity.model.Role;
import com.spring.CinemaCity.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleType(RoleType roleType);
}
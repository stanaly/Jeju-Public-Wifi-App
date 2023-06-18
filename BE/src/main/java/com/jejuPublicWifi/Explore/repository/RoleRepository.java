package com.jejuPublicWifi.Explore.repository;

import com.jejuPublicWifi.Explore.models.ERole;
import com.jejuPublicWifi.Explore.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}

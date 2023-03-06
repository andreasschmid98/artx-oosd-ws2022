package de.hsaugsburg.oosd.artx.repositories;

import de.hsaugsburg.oosd.artx.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}

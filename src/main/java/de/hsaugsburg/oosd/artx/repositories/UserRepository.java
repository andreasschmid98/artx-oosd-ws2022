package de.hsaugsburg.oosd.artx.repositories;

import de.hsaugsburg.oosd.artx.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}

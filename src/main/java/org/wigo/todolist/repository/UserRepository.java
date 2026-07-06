package org.wigo.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wigo.todolist.model.User;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface   UserRepository extends JpaRepository<User, UUID> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}

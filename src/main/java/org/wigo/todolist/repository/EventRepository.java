package org.wigo.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wigo.todolist.model.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findAllByUserId(UUID id);

    Optional<Event> findByUserIdAndId(UUID eventId, UUID userId);
}

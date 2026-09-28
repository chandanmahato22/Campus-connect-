package in.chandan.CampusConnect.repository;

import in.chandan.CampusConnect.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> {
        Optional<Event> findByName(String event_name);
}

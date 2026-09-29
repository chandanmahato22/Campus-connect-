package in.chandan.CampusConnect.repository;

import in.chandan.CampusConnect.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration,Long> {
    Optional<Registration> findByUid(long uid);
}

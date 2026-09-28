package in.chandan.CampusConnect.repository;

import in.chandan.CampusConnect.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration,Long> {
}

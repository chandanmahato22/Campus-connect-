package in.chandan.CampusConnect.repository;

import in.chandan.CampusConnect.entity.Club;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club,Long> {

    Optional<Club> findByName(String name);
}

package in.chandan.CampusConnect.repository;

import in.chandan.CampusConnect.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {



}

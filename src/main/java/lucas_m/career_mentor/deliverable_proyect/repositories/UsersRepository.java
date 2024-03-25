package lucas_m.career_mentor.deliverable_proyect.repositories;

import java.util.List;
import lucas_m.career_mentor.deliverable_proyect.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, String> {

  List<User> findByName(String name);

  List<User> findByLastname(String lastname);
}

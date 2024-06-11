package lucas_m.career_mentor.deliverable_proyect.services.interfaces;

import java.util.List;
import java.util.Optional;
import lucas_m.career_mentor.deliverable_proyect.models.User;

public interface UsersService {

  User saveUser(User user);

  Optional<User> getUserById(String id);

  List<User> getAllUsers();

  List<User> getUsersByName(String name);

  List<User> getUsersByLastname(String lastname);

  void deleteUserById(String id);
}

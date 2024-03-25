package lucas_m.career_mentor.deliverable_proyect.services;

import java.util.List;
import java.util.Optional;
import lucas_m.career_mentor.deliverable_proyect.models.User;
import lucas_m.career_mentor.deliverable_proyect.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

  @Autowired
  private UsersRepository usersRepository;

  public User saveUser(User user) {
    return usersRepository.save(user);
  }

  public Optional<User> getUserById(String id) {
    return usersRepository.findById(id);
  }

  public List<User> getAllUsers() {
    return usersRepository.findAll();
  }

  public List<User> getUsersByName(String name) {
    return usersRepository.findByName(name);
  }

  public List<User> getUsersByLastname(String lastname) {
    return usersRepository.findByLastname(lastname);
  }

  public void deleteUserById(String id) {
    usersRepository.deleteById(id);
  }
}

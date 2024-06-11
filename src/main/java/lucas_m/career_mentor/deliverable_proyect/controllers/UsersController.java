package lucas_m.career_mentor.deliverable_proyect.controllers;

import java.util.List;
import lucas_m.career_mentor.deliverable_proyect.models.User;
import lucas_m.career_mentor.deliverable_proyect.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsersController {

  @Autowired
  private UsersServiceImpl usersService;

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User savedUser = usersService.saveUser(user);
    return ResponseEntity.ok(savedUser);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable String id) {
    return usersService.getUserById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public List<User> getUsers() {
    return usersService.getAllUsers();
  }

  @GetMapping("/name/{name}")
  public List<User> getUsersByName(@PathVariable String name) {
    return usersService.getUsersByName(name);
  }

  @GetMapping("/lastname/{lastname}")
  public List<User> getUsersByLastname(@PathVariable String lastname) {
    return usersService.getUsersByLastname(lastname);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    usersService.deleteUserById(id);
    return ResponseEntity.ok().build();
  }
}

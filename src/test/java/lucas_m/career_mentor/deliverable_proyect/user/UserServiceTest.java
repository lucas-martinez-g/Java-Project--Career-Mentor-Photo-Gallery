package lucas_m.career_mentor.deliverable_proyect.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import lucas_m.career_mentor.deliverable_proyect.models.User;
import lucas_m.career_mentor.deliverable_proyect.repositories.UsersRepository;
import lucas_m.career_mentor.deliverable_proyect.services.interfaces.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(UsersService.class)
class UserServiceTest {

  @Autowired
  private UsersService usersService;

  @MockBean
  private UsersRepository usersRepository;

  private User user;

  @BeforeEach
  void setUp() {
    user = new User();
    user.setName("name");
    user.setLastname("lastname");
    user.setUsername("username");
    user.setPassword("password");
    user.setPhotos(List.of());
  }

  @Test
  void testSaveUser() {
    when(usersRepository.save(user)).thenReturn(user);

    User savedUser = usersService.saveUser(user);

    assertEquals(user, savedUser);
  }

  @Test
  void testGetUserById() {
    when(usersRepository.findById("1")).thenReturn(Optional.of(user));

    Optional<User> optionalUser = usersService.getUserById("1");

    assertEquals(user, optionalUser.get());
  }

  @Test
  void testGetAllUsers() {
    when(usersRepository.findAll()).thenReturn(List.of(user));

    List<User> userList = usersService.getAllUsers();

    assertEquals(List.of(user), userList);
  }

  @Test
  void testGetUsersByName() {
    when(usersRepository.findByName("name")).thenReturn(List.of(user));

    List<User> userList = usersService.getUsersByName(user.getName());

    assertEquals(List.of(user), userList);
  }

  @Test
  void testGetUsersByLastname() {
    when(usersRepository.findByLastname("lastname")).thenReturn(List.of(user));

    List<User> userList = usersService.getUsersByLastname(user.getLastname());

    assertEquals(List.of(user), userList);
  }

  @Test
  void testDeleteUserById() {
    doNothing().when(usersRepository).deleteById("1");

    usersService.deleteUserById("1");
  }
}

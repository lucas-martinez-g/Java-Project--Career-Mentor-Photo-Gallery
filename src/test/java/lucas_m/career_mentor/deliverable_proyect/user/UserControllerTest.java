package lucas_m.career_mentor.deliverable_proyect.user;

import static lucas_m.career_mentor.deliverable_proyect.utils.constants.Constants.USERS_CONTROLLER_URI;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import lucas_m.career_mentor.deliverable_proyect.controllers.UsersController;
import lucas_m.career_mentor.deliverable_proyect.models.User;
import lucas_m.career_mentor.deliverable_proyect.repositories.UsersRepository;
import lucas_m.career_mentor.deliverable_proyect.services.UsersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(UsersController.class)
class UserControllerTest {

  @MockBean
  private UsersServiceImpl usersService;

  @MockBean
  private UsersRepository usersRepository;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private final String URI = "http://localhost:8081" + USERS_CONTROLLER_URI;

  private User user;
  private ResultActions response;

  @BeforeEach
  void setUp() {
    user = new User();
    user.setName("name");
    user.setLastname("lastname");
    user.setUsername("username");
    user.setPassword("password");
  }

  // Test GET

  @Test
  void testGetUser() throws Exception {
    when(usersService.getUserById("1")).thenReturn(Optional.of(user));

    response = mockMvc.perform(get(URI + "/1"));

    response.andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(user.getName()))
        .andExpect(jsonPath("$.lastname").value(user.getLastname()))
        .andExpect(jsonPath("$.username").value(user.getUsername()))
        .andExpect(jsonPath("$.password").value(user.getPassword()));
  }

  @Test
  void testGetUsers() throws Exception {
    when(usersService.getAllUsers()).thenReturn(List.of(user));

    response = mockMvc.perform(get(URI));

    response.andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value(user.getName()))
        .andExpect(jsonPath("$[0].lastname").value(user.getLastname()))
        .andExpect(jsonPath("$[0].username").value(user.getUsername()))
        .andExpect(jsonPath("$[0].password").value(user.getPassword()));
  }


  @Test
  void testGetUsersByName() throws Exception {
    when(usersService.getUsersByName("name")).thenReturn(List.of(user));

    response = mockMvc.perform(get(URI + "/name/name"));

    response.andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value(user.getName()))
        .andExpect(jsonPath("$[0].lastname").value(user.getLastname()))
        .andExpect(jsonPath("$[0].username").value(user.getUsername()))
        .andExpect(jsonPath("$[0].password").value(user.getPassword()));
  }

  @Test
  void testGetUsersByLastname() throws Exception {
    when(usersService.getUsersByLastname("lastname")).thenReturn(List.of(user));

    response = mockMvc.perform(get(URI + "/lastname/lastname"));

    response.andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value(user.getName()))
        .andExpect(jsonPath("$[0].lastname").value(user.getLastname()))
        .andExpect(jsonPath("$[0].username").value(user.getUsername()))
        .andExpect(jsonPath("$[0].password").value(user.getPassword()));

  }

  // Test POST

  @Test
  void testCreateUser() throws Exception {
    when(usersService.saveUser(user)).thenReturn(user);

    String userJson = objectMapper.writeValueAsString(user);
    userJson = userJson.substring(0, userJson.lastIndexOf(",")).concat("}");

    response = mockMvc.perform(post(URI)
        .contentType(MediaType.APPLICATION_JSON)
        .content(userJson));

    response.andExpect(status().isOk());
  }

  // Test DELETE

  @Test
  void testDeleteUser() throws Exception {
    doNothing().when(usersService).deleteUserById("1");

    response = mockMvc.perform(delete(URI + "/1"));

    response.andExpect(status().isOk());
  }


}

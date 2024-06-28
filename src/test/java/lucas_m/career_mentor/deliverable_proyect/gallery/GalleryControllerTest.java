package lucas_m.career_mentor.deliverable_proyect.gallery;

import static lucas_m.career_mentor.deliverable_proyect.utils.constants.Constants.GALLERY_CONTROLLER_URI;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lucas_m.career_mentor.deliverable_proyect.controllers.GalleryController;
import lucas_m.career_mentor.deliverable_proyect.models.Photo;
import lucas_m.career_mentor.deliverable_proyect.models.PhotoDTO;
import lucas_m.career_mentor.deliverable_proyect.models.User;
import lucas_m.career_mentor.deliverable_proyect.repositories.GalleryRepository;
import lucas_m.career_mentor.deliverable_proyect.repositories.UsersRepository;
import lucas_m.career_mentor.deliverable_proyect.services.GalleryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(GalleryController.class)
class GalleryControllerTest {

  @MockBean
  private GalleryServiceImpl galleryService;

  @MockBean
  private UsersRepository usersRepository;

  @MockBean
  private GalleryRepository galleryRepository;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private Photo photo;
  private PhotoDTO photoDTO;
  private User user;
  private ResultActions response;

  private final String URI = "http://localhost:8081" + GALLERY_CONTROLLER_URI;

  @BeforeEach
  void setUp() {
    photo = Photo.builder()
        .id(1)
        .url("localhost")
        .createDate(LocalDate.parse("2024-06-10"))
        .updateDate(LocalDate.parse("2024-06-11"))
        .build();

    photoDTO = new PhotoDTO();
    photoDTO.setCreateDate(LocalDate.parse("2024-06-27"));
    photoDTO.setUpdateDate(LocalDate.parse("2024-06-28"));
    photoDTO.setUrl("localhost");

    user = new User();
    user.setName("name");
    user.setLastname("lastname");
    user.setUsername("username");
    user.setPassword("password");
    user.setPhotos(List.of());
  }

  //Tests GET

  @Test
  void testGetPhoto() throws Exception {
    when(galleryService.getPhotoById(1)).thenReturn(Optional.of(photo));

    response = mockMvc.perform(get(URI + "/1"));

    response.andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(photo.getId()))
        .andExpect(jsonPath("$.url").value(photo.getUrl()));
  }

  @Test
  void testGetGallery() throws Exception {
    when(galleryService.getGallery()).thenReturn(List.of(photo));

    response = mockMvc.perform(get(URI));

    response.andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(photo.getId()))
        .andExpect(jsonPath("$[0].url").value(photo.getUrl()))
        .andExpect(jsonPath("$[0].createDate").value(photo.getCreateDate().toString()))
        .andExpect(jsonPath("$[0].updateDate").value(photo.getUpdateDate().toString()));
  }

  @Test
  void testGetPhotosByUser() throws Exception {
    when(galleryService.getPhotosByUserUsername("user1")).thenReturn(List.of(photo));

    response = mockMvc.perform(get(URI + "/user/user1"));

    response.andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(photo.getId()))
        .andExpect(jsonPath("$[0].url").value(photo.getUrl()));
  }

  @Test
  void testGetPhotosByCreateDate() throws Exception {
    when(galleryService.getPhotosByCreateDate(LocalDate.parse("2024-06-10"))).thenReturn(
        List.of(photo));

    response = mockMvc.perform(get(URI + "/createDate/2024-06-10"));

    response.andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(photo.getId()))
        .andExpect(jsonPath("$[0].url").value(photo.getUrl()));
  }

  @Test
  void testGetPhotosByUpdateDate() throws Exception {
    when(galleryService.getPhotosByUpdateDate(LocalDate.parse("2024-06-11"))).thenReturn(
        List.of(photo));

    response = mockMvc.perform(get(URI + "/updateDate/2024-06-11"));

    response.andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(photo.getId()))
        .andExpect(jsonPath("$[0].url").value(photo.getUrl()));
  }

  @Test
  void testGetPhotoByUrl() throws Exception {
    when(galleryService.getPhotoByUrl("localhost")).thenReturn(Optional.of(photo));

    response = mockMvc.perform(get(URI + "/url/localhost"));

    response.andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(photo.getId()))
        .andExpect(jsonPath("$.url").value(photo.getUrl()));
  }

  //Test DELETE

  @Test
  void testDeletePhoto() throws Exception {
    doNothing().when(galleryService).deletePhotoById(1);

    response = mockMvc.perform(delete(URI + "/1"));

    response.andExpect(status().isOk());
  }
}

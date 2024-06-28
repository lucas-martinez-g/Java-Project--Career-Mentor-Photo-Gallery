package lucas_m.career_mentor.deliverable_proyect.gallery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lucas_m.career_mentor.deliverable_proyect.models.Photo;
import lucas_m.career_mentor.deliverable_proyect.models.PhotoDTO;
import lucas_m.career_mentor.deliverable_proyect.models.User;
import lucas_m.career_mentor.deliverable_proyect.repositories.GalleryRepository;
import lucas_m.career_mentor.deliverable_proyect.repositories.UsersRepository;
import lucas_m.career_mentor.deliverable_proyect.services.GalleryServiceImpl;
import lucas_m.career_mentor.deliverable_proyect.services.interfaces.GalleryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(GalleryService.class)
class GalleryServiceTest {

  @Autowired
  private GalleryServiceImpl galleryService;

  @MockBean
  private UsersRepository usersRepository;

  @MockBean
  private GalleryRepository galleryRepository;

  private Photo photo;
  private PhotoDTO photoDTO;
  private User user;

  @BeforeEach
  void setUp() {
    photo = Photo.builder()
        .id(1)
        .url("localhost")
        .createDate(LocalDate.parse("2024-06-10"))
        .updateDate(LocalDate.parse("2024-06-11"))
        .build();

    photoDTO = new PhotoDTO();
    photoDTO.setCreateDate(LocalDate.parse("2024-06-10"));
    photoDTO.setUpdateDate(LocalDate.parse("2024-06-11"));
    photoDTO.setUrl("localhost");

    user = new User();
    user.setName("name");
    user.setLastname("lastname");
    user.setUsername("username");
    user.setPassword("password");
    user.setPhotos(List.of());

    when(galleryRepository.save(any(Photo.class))).thenReturn(photo);
  }

  @Test
  void testCreatePhotoForUser() {
    when(usersRepository.findById("username")).thenReturn(Optional.of(user));
    when(galleryRepository.save(any(Photo.class))).thenReturn(photo);

    Photo createdPhoto = galleryService.createPhotoForUser("username", photoDTO);

    assertEquals(photo, createdPhoto);
  }

  @Test
  void testGetPhotoById() {
    when(galleryRepository.findById(any())).thenReturn(Optional.of(photo));

    Optional<Photo> photoOptional = galleryService.getPhotoById(1);

    assertTrue(photoOptional.isPresent());
    assertEquals(photo, photoOptional.get());
  }

  @Test
  void testGetGallery() {
    when(galleryRepository.findAll()).thenReturn(List.of(photo));

    List<Photo> gallery = galleryService.getGallery();

    assertEquals(1, gallery.size());
    assertEquals(photo, gallery.get(0));
  }

  @Test
  void testGetPhotosByUserUsername() {
    when(galleryRepository.findByUserUsername("username")).thenReturn(List.of(photo));

    List<Photo> photos = galleryService.getPhotosByUserUsername("username");

    assertEquals(1, photos.size());
    assertEquals(photo, photos.get(0));
  }

  @Test
  void testGetPhotosByCreateDate() {
    when(galleryRepository.findByCreateDate(LocalDate.parse("2024-06-10"))).thenReturn(
        List.of(photo));

    List<Photo> photos = galleryService.getPhotosByCreateDate(LocalDate.parse("2024-06-10"));

    assertEquals(1, photos.size());
    assertEquals(photo, photos.get(0));
  }

  @Test
  void testGetPhotosByUpdateDate() {
    when(galleryRepository.findByUpdateDate(LocalDate.parse("2024-06-11"))).thenReturn(
        List.of(photo));

    List<Photo> photos = galleryService.getPhotosByUpdateDate(LocalDate.parse("2024-06-11"));

    assertEquals(1, photos.size());
    assertEquals(photo, photos.get(0));
  }

  @Test
  void testGetPhotoByUrl() {
    when(galleryRepository.findByUrl("localhost")).thenReturn(Optional.of(photo));

    Optional<Photo> photoOptional = galleryService.getPhotoByUrl("localhost");

    assertTrue(photoOptional.isPresent());
    assertEquals(photo, photoOptional.get());
  }

  @Test
  void testDeletePhotoById() {
    doNothing().when(galleryRepository).deleteById(1);

    galleryService.deletePhotoById(1);
  }
}
package lucas_m.career_mentor.deliverable_proyect.controllers;

import java.time.LocalDate;
import java.util.List;
import lucas_m.career_mentor.deliverable_proyect.models.Photo;
import lucas_m.career_mentor.deliverable_proyect.models.PhotoDTO;
import lucas_m.career_mentor.deliverable_proyect.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {

  @Autowired
  private GalleryService galleryService;

  @PostMapping("/user/{username}")
  public ResponseEntity<Photo> createPhoto(@PathVariable String username,
      @RequestBody PhotoDTO photoDTO) {
    Photo createdPhoto = galleryService.createPhotoForUser(username, photoDTO);
    if (createdPhoto != null) {
      return ResponseEntity.status(HttpStatus.CREATED).body(createdPhoto);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Photo> getPhoto(@PathVariable Integer id) {
    return galleryService.getPhotoById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public List<Photo> getGallery() {
    return galleryService.getGallery();
  }

  @GetMapping("/user/{username}")
  public List<Photo> getPhotosByUser(@PathVariable String username) {
    return galleryService.getPhotosByUserUsername(username);
  }

  @GetMapping("/createDate/{createDate}")
  public List<Photo> getPhotosByCreateDate(@PathVariable LocalDate createDate) {
    return galleryService.getPhotosByCreateDate(createDate);
  }

  @GetMapping("/updateDate/{updateDate}")
  public List<Photo> getPhotosByUpdateDate(@PathVariable LocalDate updateDate) {
    return galleryService.getPhotosByUpdateDate(updateDate);
  }

  @GetMapping("/url/{url}")
  public ResponseEntity<Photo> getPhotoByUrl(@PathVariable String url) {
    return galleryService.getPhotoByUrl(url)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePhoto(@PathVariable Integer id) {
    galleryService.deletePhotoById(id);
    return ResponseEntity.ok().build();
  }
}

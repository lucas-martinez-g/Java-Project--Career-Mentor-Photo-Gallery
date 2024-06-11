package lucas_m.career_mentor.deliverable_proyect.services.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lucas_m.career_mentor.deliverable_proyect.models.Photo;
import lucas_m.career_mentor.deliverable_proyect.models.PhotoDTO;

public interface GalleryService {

  Photo createPhotoForUser(String username, PhotoDTO photoDTO);

  Optional<Photo> getPhotoById(Integer id);

  List<Photo> getGallery();

  List<Photo> getPhotosByUserUsername(String author);

  List<Photo> getPhotosByCreateDate(LocalDate createDate);

  List<Photo> getPhotosByUpdateDate(LocalDate updateDate);

  Optional<Photo> getPhotoByUrl(String url);

  void deletePhotoById(Integer id);
}

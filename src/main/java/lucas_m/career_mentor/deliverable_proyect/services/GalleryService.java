package lucas_m.career_mentor.deliverable_proyect.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lucas_m.career_mentor.deliverable_proyect.models.Photo;
import lucas_m.career_mentor.deliverable_proyect.models.PhotoDTO;
import lucas_m.career_mentor.deliverable_proyect.models.User;
import lucas_m.career_mentor.deliverable_proyect.repositories.GalleryRepository;
import lucas_m.career_mentor.deliverable_proyect.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {

  @Autowired
  private GalleryRepository galleryRepository;
  @Autowired
  private UsersRepository usersRepository;

  public Photo createPhotoForUser(String username, PhotoDTO photoDTO) {
    Optional<User> opUser = usersRepository.findById(username);
    if (opUser.isEmpty()) {
      return null;
    }
    User user = opUser.get();
    Photo photo = new Photo();
    photo.setId(photo.getId());
    photo.setCreateDate(photoDTO.getCreateDate());
    photo.setUpdateDate(photoDTO.getUpdateDate());
    photo.setUrl(photoDTO.getUrl());
    photo.setUser(user);
    return galleryRepository.save(photo);
  }

  public Optional<Photo> getPhotoById(Integer id) {
    return galleryRepository.findById(id);
  }

  public List<Photo> getGallery() {
    return galleryRepository.findAll();
  }

  public List<Photo> getPhotosByUserUsername(String author) {
    return galleryRepository.findByUserUsername(author);
  }

  public List<Photo> getPhotosByCreateDate(LocalDate createDate) {
    return galleryRepository.findByCreateDate(createDate);
  }

  public List<Photo> getPhotosByUpdateDate(LocalDate updateDate) {
    return galleryRepository.findByUpdateDate(updateDate);
  }

  public Optional<Photo> getPhotoByUrl(String url) {
    return galleryRepository.findByUrl(url);
  }

  public void deletePhotoById(Integer id) {
    galleryRepository.deleteById(id);
  }
}

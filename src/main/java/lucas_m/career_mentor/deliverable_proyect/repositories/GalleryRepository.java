package lucas_m.career_mentor.deliverable_proyect.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lucas_m.career_mentor.deliverable_proyect.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Photo, Integer> {

  List<Photo> findByUserUsername(String username);

  List<Photo> findByCreateDate(LocalDate createDate);

  List<Photo> findByUpdateDate(LocalDate updateDate);

  Optional<Photo> findByUrl(String url);
}

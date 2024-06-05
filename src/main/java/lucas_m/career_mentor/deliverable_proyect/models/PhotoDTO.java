package lucas_m.career_mentor.deliverable_proyect.models;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoDTO {

  private LocalDate createDate;
  private LocalDate updateDate;
  private String url;
}

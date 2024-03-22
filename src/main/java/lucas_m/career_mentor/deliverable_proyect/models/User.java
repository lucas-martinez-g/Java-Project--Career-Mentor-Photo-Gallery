package lucas_m.career_mentor.deliverable_proyect.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
  private String username;
  private String password;
  private String name;
  private String lastname;
  private List<Photo> photos;
}

package lucas_m.career_mentor.deliverable_proyect.models;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Photo {

  private int id;
  private String author;
  private Date createDate;
  private Date updateDate;
  private String url;
}

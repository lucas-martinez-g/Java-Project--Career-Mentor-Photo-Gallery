package lucas_m.career_mentor.deliverable_proyect.models;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoDTO {

  @NotNull(message = "Create date cannot be null")
  private LocalDate createDate;
  @NotNull(message = "Update date cannot be null")
  private LocalDate updateDate;
  @NotNull(message = "Url cannot be null")
  private String url;
}

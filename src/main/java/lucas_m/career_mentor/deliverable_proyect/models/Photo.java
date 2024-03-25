package lucas_m.career_mentor.deliverable_proyect.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Gallery")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Photo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @ManyToOne
  @JoinColumn(name = "user_username")
  @JsonIgnore
  private User user;
  @Column(name = "createDate", nullable = false)
  private LocalDate createDate;
  @Column(name = "updateDate", nullable = false)
  private LocalDate updateDate;
  @Column(name = "url", unique = true, nullable = false, length = 511)
  private String url;

  @JsonProperty("username")
  public String getUsername() {
    return user != null ? user.getUsername() : null;
  }
}

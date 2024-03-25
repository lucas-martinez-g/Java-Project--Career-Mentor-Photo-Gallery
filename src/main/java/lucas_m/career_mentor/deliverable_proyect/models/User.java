package lucas_m.career_mentor.deliverable_proyect.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
public class User {

  @Id
  private String username;
  @Column(name = "password", nullable = false)
  private String password;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "lastname", nullable = false)
  private String lastname;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<Photo> photos;

  @JsonProperty("photosIds")
  public List<Integer> getPhotosIds() {
    return (photos != null) ?
        photos.stream().map(Photo::getId).toList() :
        Collections.emptyList();
  }
}

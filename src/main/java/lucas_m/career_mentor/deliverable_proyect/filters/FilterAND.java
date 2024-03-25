package lucas_m.career_mentor.deliverable_proyect.filters;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FilterAND implements Filter {

  private Filter f1;
  private Filter f2;

  public boolean accept(Object o) {
    return f1.accept(o) && f2.accept(o);
  }
}

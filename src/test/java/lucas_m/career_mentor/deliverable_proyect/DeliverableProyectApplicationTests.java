package lucas_m.career_mentor.deliverable_proyect;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeliverableProyectApplicationTests {

  @Autowired
  private DeliverableProyectApplication deliverableProyectApplication;

  @Test
  void contextLoads() {
    assertNotNull(deliverableProyectApplication);
  }

}

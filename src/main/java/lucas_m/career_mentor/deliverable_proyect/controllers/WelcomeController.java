package lucas_m.career_mentor.deliverable_proyect.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class WelcomeController {

  @GetMapping("/welcome")
  public String welcome(Model model) {
    model.addAttribute("name", "Visitor");
    return "welcome"; // Nombre de la vista (welcome.html)
  }
}

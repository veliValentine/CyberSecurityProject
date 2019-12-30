package sec.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Login;
import sec.project.repository.LoginRepository;

@Controller
public class BrokenAccessController {

    
    @Autowired
    LoginRepository loginRepository;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String name, @RequestParam(required = false) String pass1) {
        if (name == null || pass1 == null || name.trim().isEmpty() || pass1.trim().isEmpty()) {
            return "login";
        }

        for (Login l : loginRepository.findAll()) {
            if (l.getUsername().equals(name.trim().toLowerCase())) {
                if(l.isAdmin()) {
                    return "redirect:/admin";
                } else {
                    break;
                }
            }
        }
        return "redirect:/user";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user() {
        return "user";
    }

}

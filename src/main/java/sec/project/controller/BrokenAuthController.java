package sec.project.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Login;
import sec.project.repository.LoginRepository;

@Controller
public class BrokenAuthController {

    @Autowired
    LoginRepository loginRepository;

    @PostConstruct
    public void init() {
        Login l = new Login("user", "user");
        loginRepository.save(l);
        Login a = new Login("admin", "admin", true);
        loginRepository.save(a);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String login() {
        return "signup";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String newUser(@RequestParam String name, @RequestParam String pass1, @RequestParam String pass2) {
        if (name == null || pass1 == null || pass2 == null) {
            System.out.println("\n\n");
            System.out.println("*********************************************************");
            System.out.println("Null");
            System.out.println("*********************************************************");
            System.out.println("\n\n");
            return "redirect:/signup";
        }

        if (name.trim().isEmpty() || pass1.trim().isEmpty() || pass2.trim().isEmpty()) {
            System.out.println("\n\n");
            System.out.println("*********************************************************");
            System.out.println("Empty");
            System.out.println("*********************************************************");
            System.out.println("\n\n");
            return "redirect:/signup";
        }

        if (!pass1.equals(pass2)) {
            System.out.println("\n\n");
            System.out.println("*********************************************************");
            System.out.println("PasswordsNotSame");
            System.out.println("*********************************************************");
            System.out.println("\n\n");
            return "redirect:/signup";
        }

        for (Login l : loginRepository.findAll()) {
            if (l.getUsername().equals(name.trim().toLowerCase())) {
                System.out.println("\n\n");
                System.out.println("*********************************************************");
                System.out.println("UserNameIsInUse");
                System.out.println("*********************************************************");
                System.out.println("\n\n");
                return "redirect:/signup";
            }
        }

        Login l = new Login(name, pass1, false);
        loginRepository.save(l);
        System.out.println("\n\n");
        System.out.println("*********************************************************");
        System.out.println("new user " + l.getUsername() + ", " + l.getPassword());
        System.out.println("*********************************************************");
        System.out.println("\n\n");
        return "redirect:/login";
    }
}

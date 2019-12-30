package sec.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
public class FlawController {

    
    
    @RequestMapping(value = "*", method = RequestMethod.GET)
    public String load() {
        return "redirect:/";
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadForm() {
        return "flaw";
    }
    
    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public String directInjection() {
        return "redirect:/injection";
    }
    
    @RequestMapping(value = "/2", method = RequestMethod.GET)
    public String directAuthentication() {
        return "redirect:/signup";
    }
    
    @RequestMapping(value = "/3", method = RequestMethod.GET)
    public String directAccess() {
        return "redirect:/login";
    }
    
    @RequestMapping(value = "/4", method = RequestMethod.GET)
    public String directXSS() {
        return "redirect:/xss";
    }
    
    @RequestMapping(value = "/5", method = RequestMethod.GET)
    public String directInsufficent() {
        return "redirect:/login";
    }
}

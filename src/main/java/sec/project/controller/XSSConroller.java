package sec.project.controller;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class XSSConroller {

    private ArrayList<String> comments;
    
    @PostConstruct
    public void init() {
        comments = new ArrayList<>();
    }
    
    @RequestMapping(value = "/xss", method = RequestMethod.GET)
    public String xss(Model model) {
        model.addAttribute("list", comments);
        return "xss";
    }
    
    @RequestMapping(value = "/xss", method = RequestMethod.POST)
    public String addComment(@RequestParam String comment) {
        comments.add(comment);
        return "redirect:/xss";
    }
}
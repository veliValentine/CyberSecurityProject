package sec.project.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InjectionController {

    @PostConstruct
    public void init() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:h2:file:./inj", "sa", "");
        connection.createStatement().execute("DROP TABLE Persons");
        connection.createStatement().execute("CREATE TABLE Persons (Name varchar(255), Date int, Month int, Year int)");
        connection.createStatement().execute("INSERT INTO Persons VALUES ('Dave', '1', '1', '2000')");
        connection.createStatement().execute("INSERT INTO Persons VALUES ('FutureDave', '1', '1', '2200')");
        connection.createStatement().execute("INSERT INTO Persons VALUES ('SENSITIVE DATA', '1', '1', '2200')");
    }

    private ArrayList<String> list = null;

    @RequestMapping(value = "/injection", method = RequestMethod.GET)
    public String loadForm(Model model) {
        if (list != null) {
            model.addAttribute("list", list);
        }
        return "injection";
    }

    @RequestMapping(value = "/injection", method = RequestMethod.POST)
    public String getName(@RequestParam String n) throws Exception {

        list = new ArrayList<>();
        if(n.contains("drop")) {
            list.add("F YOU");
            return "redirect:/injection";
        }
        
        /*  FIX TO INJECTION FLAW
        String c = "\"!#¤%&/()=?`´'*";
        for (int i = 0; i < c.length(); i++) {
            if(n.contains("" + c.charAt(i))) {
                list.add("NO");
                return "redirect:/injection";
            }
        }
        */
        Connection connection = DriverManager.getConnection("jdbc:h2:file:./inj", "sa", "");
        
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Persons WHERE Name='" + n + "'");//" + n + "

        while (resultSet.next()) {
            String name = resultSet.getString("Name");
            list.add(name);
        }

        resultSet.close();
        connection.close();

        return "redirect:/injection";
    }
}

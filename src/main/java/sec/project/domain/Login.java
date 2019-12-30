package sec.project.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Login extends AbstractPersistable<Long>  {

    private String username;
    private String password;  
    private boolean admin;
    
    public Login(){
    }
    
    public Login(String username, String passwords) {
        this.username = username.trim().toLowerCase();
        this.password = passwords;
    }
    
    public Login(String username, String passwords, boolean admin) {
        this.username = username;
        this.password = passwords;
        this.admin = admin;
    }

    public String getUsername() {
        return username.trim().toLowerCase();
    }

    public void setUsername(String username) {
        this.username = username.trim().toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    
}

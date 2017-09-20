package nefalas.kthplanner.login;

import nefalas.webreader.WebReader;
import nefalas.webreader.sessionmanager.SessionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @RequestMapping("/login")
    public Login login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        return new Login(username, password);
    }

}

class Login {

    private boolean isLoggedIn;

    Login(String username, String password) {
        isLoggedIn = SessionManager.getWebClientByUsername(username, password) != null;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

}
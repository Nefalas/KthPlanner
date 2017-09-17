package nefalas.kthplanner.login;

import nefalas.webreader.CanvasReader;
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

    public Login(String username, String password) {
        isLoggedIn = new CanvasReader(username, password).login();
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

}
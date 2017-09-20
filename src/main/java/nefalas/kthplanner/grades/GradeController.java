package nefalas.kthplanner.grades;

import nefalas.webreader.KthReader;
import nefalas.webreader.sessionmanager.SessionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GradeController {

    @RequestMapping("/kthgrades")
    public List<KthGrade> kthGrades(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        return new ArrayList<>();
    }

}

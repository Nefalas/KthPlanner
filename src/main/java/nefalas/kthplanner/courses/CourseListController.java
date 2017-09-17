package nefalas.kthplanner.courses;

import nefalas.webreader.CanvasReader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseListController {

    @RequestMapping("/courselist")
    public List<Course> courseListController (
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        CanvasReader canvasReader = new CanvasReader(username, password);
        if (canvasReader.login()) {
            return canvasReader.getCurrentCourses();
        }
        return new ArrayList<>();
    }

}

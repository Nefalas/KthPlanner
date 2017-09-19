package nefalas.kthplanner.courses;

import nefalas.webreader.CanvasReader;
import nefalas.webreader.KthReader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseListController {

    @RequestMapping("/courselist")
    public List<CanvasCourse> courseList (
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        CanvasReader canvasReader = new CanvasReader(username, password);
        if (canvasReader.login()) {
            return canvasReader.getCurrentCourses();
        }
        return new ArrayList<>();
    }

    @RequestMapping("/pastcourses")
    public List<CanvasCourse> pastCourses (
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        CanvasReader canvasReader = new CanvasReader(username, password);
        if (canvasReader.login()) {
            return canvasReader.getPastCourses();
        }
        return new ArrayList<>();
    }

    @RequestMapping("/futurecourses")
    public List<CanvasCourse> futureCourses (
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        CanvasReader canvasReader = new CanvasReader(username, password);
        if (canvasReader.login()) {
            return canvasReader.getFutureCourses();
        }
        return new ArrayList<>();
    }

    @RequestMapping("/kthcourses")
    public List<KthCourse> kthCourses (
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        KthReader kthReader = new KthReader(username, password);
        if (kthReader.login()) {
            return kthReader.getCourses();
        }
        return new ArrayList<>();
    }

}

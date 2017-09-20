package nefalas.kthplanner.courses;

import nefalas.webreader.CanvasReader;
import nefalas.webreader.KthReader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    @RequestMapping("/courselist")
    public List<CanvasCourse> courseList (
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        CanvasReader canvasReader = new CanvasReader(username, password);
        return canvasReader.getCurrentCourses();
    }

    @RequestMapping("/pastcourses")
    public List<CanvasCourse> pastCourses (
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        CanvasReader canvasReader = new CanvasReader(username, password);
        return canvasReader.getPastCourses();
    }

    @RequestMapping("/futurecourses")
    public List<CanvasCourse> futureCourses (
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        CanvasReader canvasReader = new CanvasReader(username, password);
        return canvasReader.getFutureCourses();
    }

    @RequestMapping("/kthcourses")
    public List<KthCourse> kthCourses (
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        KthReader kthReader = new KthReader(username, password);
        return kthReader.getCourses();
    }

}

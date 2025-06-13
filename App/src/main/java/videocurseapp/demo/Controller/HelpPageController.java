package videocurseapp.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import videocurseapp.demo.Model.Course;
import videocurseapp.demo.Model.User;
import videocurseapp.demo.Service.UserService;

@Controller
public class HelpPageController {

    @Autowired
    private UserService userService;
    private ControllerStaticParent parent = new ControllerStaticParent();

    //translation endpint code: help
    @GetMapping("/help")
    public String account( Model model ) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_help_title));
            
            List<Course> courses = userService.getHelpCourses();
            model.addAttribute("courseList", courses);
            boolean thereAre = false;
            if (courses!=null && courses.size()>0) {
                thereAre = true;
            }
            model.addAttribute("thereAreCourses", thereAre);
            model.addAttribute("noCourse", parent.getString(parent.MSG_help_noCourse));
            return "help";
        }
        return "redirect:/logout";
    }
}

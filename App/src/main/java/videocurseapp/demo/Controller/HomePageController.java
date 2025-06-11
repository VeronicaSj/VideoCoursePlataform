package videocurseapp.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import videocurseapp.demo.Model.Course;
import videocurseapp.demo.Model.User;
import videocurseapp.demo.Service.CourseService;
import videocurseapp.demo.Service.ImageService;
import videocurseapp.demo.Service.UserService;

@Controller
public class HomePageController {

    @Autowired
    private ImageService filesStorageService;

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    private ControllerStaticParent parent = new ControllerStaticParent();

    @GetMapping("/home")
    public String home( Model model ) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  "My Bought Courses");
            List<Course> boughtList = user.getUserCourses();
            model.addAttribute("courseList", boughtList);
            boolean thereAre = false;
            if (boughtList!=null && boughtList.size()>0) {
                thereAre = true;
            }
            model.addAttribute("thereAreCourses", thereAre);
        return "home";
        }
        return "redirect:/logout";
    }

    @PostMapping("/search")
    public String home( Model model, 
        @RequestParam("search") String search) {
        User user = userService.findInUseUser();
        if(user != null){
            if(search!=null && search.length()>0){
                model = parent.basicModelGenerator(user, model,  "Search results for: "+search);
                
                List<Course> resList = courseService.searchCourses(search);
                model.addAttribute("courseList", resList);
                boolean thereAre = false;
                if (resList!=null && resList.size()>0) {
                    thereAre = true;
                }
                model.addAttribute("thereAreCourses", thereAre);

                return "search";
            }
        return "redirect:/home";
        }
        return "redirect:/logout";
    }
    
    @GetMapping("/courses/buy/{id}")
    public String buycourse( Model model, 
        @PathVariable long id) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  "Buy course");
            Course course = courseService.find(id);
            if (course!=null) {
                model.addAttribute("title", "Buy course: "+course.getName());
                model.addAttribute("course", course);
                model.addAttribute("msg", "Are you sure you want to delete this course?");
                String btnbuyMsg="buy";
                boolean btnbuydisabled=false;
                if (userService.getBoughtCourses(user).contains(course)) {
                    btnbuyMsg = "Already Bought";
                    btnbuydisabled=true;
                }
                model.addAttribute("btnbuyMsg", btnbuyMsg);
                model.addAttribute("btnbuydisabled", btnbuydisabled);
            }
            return "course_buy";
        }
        return "redirect:/logout";
    }
    
    @GetMapping("/courses/buy/form/{id}")
    public String formbuycourse( Model model, 
        @PathVariable long id) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  "Buy course");
            Course course = courseService.find(id);
            if (course!=null) {
                if(course.getPrice()>0){
                    model.addAttribute("title", "Buy course: "+course.getName());
                    model.addAttribute("course", course);
                    String btnbuyMsg="buy";
                    model.addAttribute("btnbuyMsg", btnbuyMsg);
                }else{
                    user.addUserCourses(course);
                    userService.update(user);
                    return "redirect:/home";
                }
            }
            return "course_buy_form";
        }
        return "redirect:/logout";
    }
}

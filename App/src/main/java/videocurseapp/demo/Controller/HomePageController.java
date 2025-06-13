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

    //translation endpint code: home1
    @GetMapping("/home")
    public String home( Model model ) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_home1_title));
            List<Course> boughtList = user.getUserCourses();
            model.addAttribute("courseList", boughtList);
            boolean thereAre = false;
            if (boughtList!=null && boughtList.size()>0) {
                thereAre = true;
            }
            model.addAttribute("thereAreCourses", thereAre);
            model.addAttribute("noCourseMsg", parent.getString(parent.MSG_home1_noCourseMsg));
            model.addAttribute("searchLbl", parent.getString(parent.MSG_home1_searchLbl));
            model.addAttribute("searchBtn", parent.getString(parent.MSG_home1_searchBtn));
        return "home";
        }
        return "redirect:/logout";
    }

    //translation endpint code: home2
    @PostMapping("/search")
    public String home( Model model, 
        @RequestParam("search") String search) {
        User user = userService.findInUseUser();
        if(user != null){
            if(search!=null && search.length()>0){
                model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_home2_title)+search);
                
                List<Course> resList = courseService.searchCourses(search);
                model.addAttribute("courseList", resList);
                boolean thereAre = false;
                if (resList!=null && resList.size()>0) {
                    thereAre = true;
                }
                model.addAttribute("thereAreCourses", thereAre);
                model.addAttribute("noCourseMsg", parent.getString(parent.MSG_home2_noCourseMsg));
                model.addAttribute("searchLbl", parent.getString(parent.MSG_home2_searchLbl));
                model.addAttribute("searchBtn", parent.getString(parent.MSG_home2_searchBtn));
                
                return "search";
            }
        return "redirect:/home";
        }
        return "redirect:/logout";
    }
    
    //translation endpint code: home3
    @GetMapping("/courses/buy/{id}")
    public String buycourse( Model model, 
        @PathVariable long id) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_home3_title1));
            Course course = courseService.find(id);
            if (course!=null) {
                model.addAttribute("title", parent.getString(parent.MSG_home3_title2)+course.getName());
                model.addAttribute("course", course);
                model.addAttribute("msg", parent.getString(parent.MSG_home3_msg));
                String btnbuyMsg=parent.getString(parent.MSG_home3_btnbuyMsg1);
                boolean btnbuydisabled=false;
                if (userService.getBoughtCourses(user).contains(course)) {
                    btnbuyMsg = parent.getString(parent.MSG_home3_btnbuyMsg2);
                    btnbuydisabled=true;
                }

                model.addAttribute("courseTitleLbl", parent.getString(parent.MSG_home3_courseTitleLbl));
                model.addAttribute("DescriptionLbl", parent.getString(parent.MSG_home3_DescriptionLbl));
                model.addAttribute("PriceLbl", parent.getString(parent.MSG_home3_PriceLbl));
                model.addAttribute("coinLbl", parent.getString(parent.MSG_home3_coinLbl));
                model.addAttribute("publicLbl", parent.getString(parent.MSG_home3_publicLbl));
                model.addAttribute("videoListLbl", parent.getString(parent.MSG_home3_videoListLbl));
                model.addAttribute("btnbuyMsg", btnbuyMsg);
                model.addAttribute("btnbuydisabled", btnbuydisabled);
            }
            return "course_buy";
        }
        return "redirect:/logout";
    }
    
    //translation endpint code: home4
    @GetMapping("/courses/buy/form/{id}")
    public String formbuycourse( Model model, 
        @PathVariable long id) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_home4_title1));
            Course course = courseService.find(id);
            if (course!=null) {
                if(course.getPrice()>0){
                    model.addAttribute("title", parent.getString(parent.MSG_home4_title2)+course.getName());
                    model.addAttribute("course", course);
                    String btnbuyMsg=parent.getString(parent.MSG_home4_btnbuyMsg);
                    model.addAttribute("btnbuyMsg", btnbuyMsg);
                    model.addAttribute("subtitle", parent.getString(parent.MSG_home4_subtitle));
                    model.addAttribute("aceptedCardsLbl", parent.getString(parent.MSG_home4_aceptedCardsLbl));
                    model.addAttribute("totalLbl", parent.getString(parent.MSG_home4_totalLbl));
                    model.addAttribute("currencyLbl", parent.getString(parent.MSG_home4_currencyLbl));
                    model.addAttribute("methodLbl", parent.getString(parent.MSG_home4_methodLbl));
                    model.addAttribute("intentLbl", parent.getString(parent.MSG_home4_intentLbl));
                    model.addAttribute("descriptionLbl", parent.getString(parent.MSG_home4_descriptionLbl));
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

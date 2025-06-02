package videocurseapp.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import videocurseapp.demo.Model.Course;
import videocurseapp.demo.Model.Image;
import videocurseapp.demo.Model.User;
import videocurseapp.demo.Service.CourseService;
import videocurseapp.demo.Service.ImageService;
import videocurseapp.demo.Service.UserService;
import videocurseapp.demo.Utilities.NavLink;

@Controller
public class CourseController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    private ControllerStaticParent parent = new ControllerStaticParent();

    

    @GetMapping("/courses")
    public String courses( Model model ) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  "My Courses");
        
            //side bar options
            ArrayList<NavLink> sideBarLinks = new ArrayList<NavLink>();
            sideBarLinks.add(new NavLink("/courses/upload", "Upload Course"));
            model.addAttribute("sideBarLinks", sideBarLinks);

            List<Course> courses = user.getCreatedCourses();
            courses.add(new Course());
            model.addAttribute("courseList", courses);
            boolean thereAre = false;
            if (courses.size()>0) {
                thereAre = true;
            }
            model.addAttribute("thereAreCourses", thereAre);
            return "courses";
        }
        return "redirect:/logout";
    }

    @GetMapping("/courses/upload")
    public String coursesUpload( Model model) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  "Upload Course");

            boolean validName = true;
            boolean validDescription = true;
            boolean validPrice = true;
            boolean validCoin = true;
            boolean validIsPublic = true;

            model.addAttribute("nameLbl", "Title");
            model.addAttribute("descriptionLbl", "Description");
            model.addAttribute("priceLbl", "Price");
            model.addAttribute("coinLbl", "Coin");
            model.addAttribute("isPublicLbl", "Visibility");

            model.addAttribute("nameValue", "");
            model.addAttribute("descriptionValue", "");
            model.addAttribute("priceValue", 0);
                model.addAttribute("coinValueEUR", true);
                model.addAttribute("coinValueUSD", false);
                model.addAttribute("isPublicValuePublic", true);
                model.addAttribute("isPublicValuePrivated", false);

            model.addAttribute("nameError", !validName);
            model.addAttribute("descriptionError", !validDescription);
            model.addAttribute("priceError", !validPrice);
            model.addAttribute("coinError", !validCoin);
            model.addAttribute("isPublicError", !validIsPublic);

            model.addAttribute("nameErrorMsg", "Invalid name");
            model.addAttribute("descriptionErrorMsg", "Invalid description");
            model.addAttribute("priceErrorMsg", "Invalid price");
            model.addAttribute("coinErrorMsg", "Invalid coin");
            model.addAttribute("isPublicErrorMsg", "Invalid visibility");

            model.addAttribute("btnNextMsg", "Next");
            
            return "courses_upload";
        }
        return "redirect:/logout";
    }


    @PostMapping("/courses/upload")
    public String postCoursesUploadImg( Model model,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") float price, 
            @RequestParam("coin") String coin,
            @RequestParam("ispublic") String ispublic) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  "Upload Course");
            
            System.out.println("name " + name + " description " + " price " + price + " coin " + coin + " ispublic " + ispublic );
            //validate data 
            boolean validName = name.trim().length()>0 ;
            boolean validDescription = description.trim().length()>0;
            boolean validPrice = price>=0;
            boolean validCoin = coin.equals("EUR") || coin.equals("USD");
            boolean validIsPublic = ispublic.equals("Public") || ispublic.equals("Privated");

            boolean validDataInput = validName && validDescription && validPrice && validCoin && validIsPublic;
            if (!validDataInput) {
                model.addAttribute("nameLbl", "Title");
                model.addAttribute("descriptionLbl", "Description");
                model.addAttribute("priceLbl", "Price");
                model.addAttribute("coinLbl", "Coin");
                model.addAttribute("isPublicLbl", "Visibility");

                model.addAttribute("nameValue", name);
                model.addAttribute("descriptionValue", description);
                model.addAttribute("priceValue", price);
                model.addAttribute("coinValueEUR", coin.equals("EUR"));
                model.addAttribute("coinValueUSD", coin.equals("USD"));
                model.addAttribute("isPublicValuePublic", ispublic.equals("Public"));
                model.addAttribute("isPublicValuePrivated", ispublic.equals("Privated"));

                model.addAttribute("nameError", !validName);
                model.addAttribute("descriptionError", !validDescription);
                model.addAttribute("priceError", !validPrice);
                model.addAttribute("coinError", !validCoin);
                model.addAttribute("isPublicError", !validIsPublic);

                model.addAttribute("nameErrorMsg", "Invalid name");
                model.addAttribute("descriptionErrorMsg", "Invalid description");
                model.addAttribute("priceErrorMsg", "Invalid price");
                model.addAttribute("coinErrorMsg", "Invalid coin");
                model.addAttribute("isPublicErrorMsg", "Invalid visibility");

                model.addAttribute("btnNextMsg", "Next");

                return "courses_upload";
            }

            //save somewhere course data
            Course.Coin enumCoin = Course.Coin.valueOf(coin.toUpperCase());
            boolean boolIspublic = ispublic.equals("Public");
            courseService.setTempCourse(new Course(name, description, price, enumCoin, boolIspublic));

            return "redirect:/images/upload/courses*upload*video";
        }
        return "redirect:/logout";
    }

    @GetMapping("/courses/upload/video/{imgId}")
    public String coursesUploadVideo( Model model, 
            @PathVariable long imgId) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  "Upload Course");

            //add image to the temporal course
            Course course = courseService.getTempCourse();
            course.setImg(imageService.load(imgId));
            courseService.setTempCourse(course);
            
            return "video_upload";
        }
        return "redirect:/logout";
    }

    @PostMapping("/courses/upload/video")
    public String postCoursesUploadVideo( Model model ) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  "Upload Course");
            
            //add video list to the temporal course
            Course course = courseService.getTempCourse();
            
            courseService.setTempCourse(course);

            return "redirect:/courses/upload/img";
        }
        return "redirect:/logout";
    }
}
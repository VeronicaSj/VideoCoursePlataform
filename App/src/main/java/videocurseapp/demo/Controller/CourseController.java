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
import videocurseapp.demo.Model.User;
import videocurseapp.demo.Model.Video;
import videocurseapp.demo.Service.CourseService;
import videocurseapp.demo.Service.ImageService;
import videocurseapp.demo.Service.UserService;
import videocurseapp.demo.Service.VideoService;
import videocurseapp.demo.Utilities.NavLink;


@Controller
public class CourseController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired 
    private VideoService videoService;

    private ControllerStaticParent parent = new ControllerStaticParent();

    private Course tempCourse;

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
            model.addAttribute("courseList", courses);
            boolean thereAre = false;
            if (courses!=null && courses.size()>0) {
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
    public String postCoursesUpload( Model model,
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
            tempCourse = new Course(name, description, price, enumCoin, boolIspublic);

            return "redirect:/images/upload/courses*upload*img";
        }
        return "redirect:/logout";
    }

    @GetMapping("/courses/upload/img/{imgId}")
    public String coursesUploadImg( Model model, 
            @PathVariable long imgId) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  "Upload Course - Manage Videos");
            //add image to the temporal course
            tempCourse.setImg(imageService.load(imgId));

            return "redirect:/courses/upload/video";
        }
        return "redirect:/logout";
    }

    @GetMapping("/courses/upload/video")
    public String coursesUploadVideo( Model model) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  "Upload Course - Manage Videos");

            // model.addAttribute("videolist", courseService.listVideos(course));
            model.addAttribute("btnAddNewMsg", "Add New Video");
            model.addAttribute("btnAddNewHref", "/videos/upload");
            model.addAttribute("btnNextMsg", "Next");
            model.addAttribute("btnNextHref", "/courses/upload/sumary");
            System.out.println(tempCourse.getVideos());
            model.addAttribute("videolist", courseService.listVideos(tempCourse));
            return "course_video_upload";

        }
        return "redirect:/logout";
    }

    @GetMapping("/videos/upload")
    public String newVideo(Model model) {
        User user = (User) userService.findInUseUser();
        model = parent.basicModelGenerator(user, model,  "Upload Video");
        model.addAttribute("btnUploadMsg", "Upload Video");
        
        return "video_upload";
    }

    @PostMapping("/videos/upload")
    public String postNewVideo(Model model, 
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title) {
        User user = (User) userService.findInUseUser();
        model = parent.basicModelGenerator(user, model,  "Upload Video");
        
        String msg = "You have to choose a video to upload";
        boolean titleError=false;
        boolean videoError=true;
        String titleErrorMsg="Invalid Title";

        String classs ="badMsgDiv";
        if (title!=null && title.trim().length()>0) {
            if(!file.isEmpty()){
                try {
                    Video newVideo = videoService.saveNewVideo(title, file);
                    tempCourse.addVideo(newVideo);
                    msg = "Uploaded the video successfully: " + file.getOriginalFilename();
                    classs ="okMsgDiv";
                    videoError=false;
                } catch (Exception e) {
                    msg = "Could not upload the video: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
                }
            }
        }

        if(!titleError && !videoError){
            return "redirect:/courses/upload/video";
        }

        model.addAttribute("titleError", titleError);
        model.addAttribute("titleErrorMsg", titleErrorMsg);
        model.addAttribute("videoTitle", "");
        model.addAttribute("message", msg);
        model.addAttribute("classs", classs);
        model.addAttribute("btnUploadMsg", "Upload video");
        
        return "video_upload";
    }

    @GetMapping("/courses/upload/sumary")
    public String courseSumary( Model model ) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  "Upload Course - Sumary");
            //add image to the temporal course
            System.out.println("image: " + tempCourse.getImg());

            model.addAttribute("img", tempCourse.getImg());
            model.addAttribute("course", tempCourse);
            
            model.addAttribute("btnNextMsg", "Upload course");
            return "course_video_upload_sumary";
        }
        return "redirect:/logout";
    }

    @PostMapping("/courses/upload/sumary")
    public String postCourseSumary(Model model) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  "Upload Course - Confirmation");
            String msg = "Course could not be uploaded";
            if(courseService.save(tempCourse)){
                msg = "Course uploaded";
            }
            model.addAttribute("msg", msg);
            return "confirmation_msg";
        }
        return "redirect:/logout";
    }
    
}
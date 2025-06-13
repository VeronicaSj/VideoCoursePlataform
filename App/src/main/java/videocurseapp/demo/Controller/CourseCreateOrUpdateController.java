package videocurseapp.demo.Controller;

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


@Controller
public class CourseCreateOrUpdateController {
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

    //translation endpint code: course7
    @GetMapping("/courses/upload")
    public String coursesUpload( Model model) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_course7_title));
            tempCourse = new Course();

            boolean validName = true;
            boolean validDescription = true;
            boolean validPrice = true;
            boolean validCoin = true;
            boolean validIsPublic = true;

            model.addAttribute("nameLbl", parent.getString(parent.MSG_course7_nameLbl));
            model.addAttribute("descriptionLbl", parent.getString(parent.MSG_course7_descriptionLbl));
            model.addAttribute("priceLbl", parent.getString(parent.MSG_course7_priceLbl));
            model.addAttribute("coinLbl", parent.getString(parent.MSG_course7_coinLbl));
            model.addAttribute("isPublicLbl", parent.getString(parent.MSG_course7_isPublicLbl));

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

            model.addAttribute("nameErrorMsg", parent.getString(parent.MSG_course7_nameErrorMsg));
            model.addAttribute("descriptionErrorMsg", parent.getString(parent.MSG_course7_descriptionErrorMsg));
            model.addAttribute("priceErrorMsg", parent.getString(parent.MSG_course7_priceErrorMsg));
            model.addAttribute("coinErrorMsg", parent.getString(parent.MSG_course7_coinErrorMsg));
            model.addAttribute("isPublicErrorMsg", parent.getString(parent.MSG_course7_isPublicErrorMsg));

            model.addAttribute("btnNextMsg", parent.getString(parent.MSG_course7_btnNextMsg));
            
            model.addAttribute("btnNextHref", "/courses/upload");

            return "courses_upload";
        }
        return "redirect:/logout";
    }


    //translation endpint code: course8
    @PostMapping("/courses/upload")
    public String postCoursesUpload( Model model,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") float price, 
            @RequestParam("coin") String coin,
            @RequestParam("ispublic") String ispublic) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_course8_title));
            
            System.out.println("name " + name + " description " + " price " + price + " coin " + coin + " ispublic " + ispublic );
            //validate data 
            boolean validName = name.trim().length()>0 ;
            boolean validDescription = description.trim().length()>0;
            boolean validPrice = price>=0;
            boolean validCoin = coin.equals("EUR") || coin.equals("USD");
            boolean validIsPublic = ispublic.equals("Public") || ispublic.equals("Privated");

            boolean validDataInput = validName && validDescription && validPrice && validCoin && validIsPublic;
            if (!validDataInput) {
                model.addAttribute("nameLbl", parent.getString(parent.MSG_course8_nameLbl));
                model.addAttribute("descriptionLbl", parent.getString(parent.MSG_course8_descriptionLbl));
                model.addAttribute("priceLbl", parent.getString(parent.MSG_course8_priceLbl));
                model.addAttribute("coinLbl", parent.getString(parent.MSG_course8_coinLbl));
                model.addAttribute("isPublicLbl", parent.getString(parent.MSG_course8_isPublicLbl));

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

                model.addAttribute("nameErrorMsg", parent.getString(parent.MSG_course8_nameErrorMsg));
                model.addAttribute("descriptionErrorMsg", parent.getString(parent.MSG_course8_descriptionErrorMsg));
                model.addAttribute("priceErrorMsg", parent.getString(parent.MSG_course8_priceErrorMsg));
                model.addAttribute("coinErrorMsg", parent.getString(parent.MSG_course8_coinErrorMsg));
                model.addAttribute("isPublicErrorMsg", parent.getString(parent.MSG_course8_isPublicErrorMsg));

                model.addAttribute("btnNextMsg", parent.getString(parent.MSG_course8_btnNextMsg));

                return "courses_upload";
            }

            //save somewhere course data
            Course.Coin enumCoin = Course.Coin.valueOf(coin.toUpperCase());
            boolean boolIspublic = ispublic.equals("Public");
            tempCourse.setName(name);
            tempCourse.setDescription(description);
            tempCourse.setPrice(price);
            tempCourse.setCoin(enumCoin);
            tempCourse.setIsPublic(boolIspublic);
            tempCourse = courseService.save(tempCourse);
            user.addcreatedCourse(tempCourse);
            userService.update(user);
            return "redirect:/images/upload/courses*img";
        }
        return "redirect:/logout";
    }

    //translation endpint code: course9
    @GetMapping("/courses/update/{id}")
    public String coursesUpdate( Model model,
        @PathVariable long id ) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            tempCourse = courseService.find(id);
            model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_course9_title) + tempCourse.getName());

            boolean validName = true;
            boolean validDescription = true;
            boolean validPrice = true;
            boolean validCoin = true;
            boolean validIsPublic = true;

            model.addAttribute("nameLbl", parent.getString(parent.MSG_course9_nameLbl));
            model.addAttribute("descriptionLbl", parent.getString(parent.MSG_course9_descriptionLbl));
            model.addAttribute("priceLbl", parent.getString(parent.MSG_course9_priceLbl));
            model.addAttribute("coinLbl", parent.getString(parent.MSG_course9_coinLbl));
            model.addAttribute("isPublicLbl", parent.getString(parent.MSG_course9_isPublicLbl));

            model.addAttribute("nameValue", tempCourse.getName());
            model.addAttribute("descriptionValue", tempCourse.getDescription());
            model.addAttribute("priceValue", tempCourse.getPrice());
                
            model.addAttribute("coinValueEUR",  tempCourse.getCoin().equals(Course.Coin.EUR));
            model.addAttribute("coinValueUSD", tempCourse.getCoin().equals(Course.Coin.USD));

            model.addAttribute("isPublicValuePublic", tempCourse.getIsPublic());
            model.addAttribute("isPublicValuePrivated", tempCourse.getIsPublic());

            model.addAttribute("nameError", !validName);
            model.addAttribute("descriptionError", !validDescription);
            model.addAttribute("priceError", !validPrice);
            model.addAttribute("coinError", !validCoin);
            model.addAttribute("isPublicError", !validIsPublic);

            model.addAttribute("nameErrorMsg", parent.getString(parent.MSG_course9_nameErrorMsg));
            model.addAttribute("descriptionErrorMsg", parent.getString(parent.MSG_course9_descriptionErrorMsg));
            model.addAttribute("priceErrorMsg", parent.getString(parent.MSG_course9_priceErrorMsg));
            model.addAttribute("coinErrorMsg", parent.getString(parent.MSG_course9_coinErrorMsg));
            model.addAttribute("isPublicErrorMsg", parent.getString(parent.MSG_course9_isPublicErrorMsg));

            model.addAttribute("btnNextMsg", parent.getString(parent.MSG_course9_btnNextMsg));
            model.addAttribute("btnNextHref", "/courses/update/"+id);
            return "courses_upload";
        }
        return "redirect:/logout";
    }


    //translation endpint code: course10
    @PostMapping("/courses/update/{id}")
    public String postCoursesUpload( Model model,
            @PathVariable long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") float price, 
            @RequestParam("coin") String coin,
            @RequestParam("ispublic") String ispublic) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_course9_title) + tempCourse.getName());
            
            System.out.println("name " + name + " description " + " price " + price + " coin " + coin + " ispublic " + ispublic );
            //validate data 
            boolean validName = name.trim().length()>0 ;
            boolean validDescription = description.trim().length()>0;
            boolean validPrice = price>=0;
            boolean validCoin = coin.equals("EUR") || coin.equals("USD");
            boolean validIsPublic = ispublic.equals("Public") || ispublic.equals("Privated");

            boolean validDataInput = validName && validDescription && validPrice && validCoin && validIsPublic;
            if (!validDataInput) {
            model.addAttribute("nameLbl", parent.getString(parent.MSG_course9_nameLbl));
            model.addAttribute("descriptionLbl", parent.getString(parent.MSG_course9_descriptionLbl));
            model.addAttribute("priceLbl", parent.getString(parent.MSG_course9_priceLbl));
            model.addAttribute("coinLbl", parent.getString(parent.MSG_course9_coinLbl));
            model.addAttribute("isPublicLbl", parent.getString(parent.MSG_course9_isPublicLbl));

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

            model.addAttribute("nameErrorMsg", parent.getString(parent.MSG_course9_nameErrorMsg));
            model.addAttribute("descriptionErrorMsg", parent.getString(parent.MSG_course9_descriptionErrorMsg));
            model.addAttribute("priceErrorMsg", parent.getString(parent.MSG_course9_priceErrorMsg));
            model.addAttribute("coinErrorMsg", parent.getString(parent.MSG_course9_coinErrorMsg));
            model.addAttribute("isPublicErrorMsg", parent.getString(parent.MSG_course9_isPublicErrorMsg));

            model.addAttribute("btnNextMsg", parent.getString(parent.MSG_course9_btnNextMsg));
            model.addAttribute("btnNextHref", "/courses/update/"+id);

                return "courses_upload";
            }

            //save somewhere course data
            Course.Coin enumCoin = Course.Coin.valueOf(coin.toUpperCase());
            boolean boolIspublic = ispublic.equals("Public");
            
            tempCourse.setName(name);
            tempCourse.setDescription(description);
            tempCourse.setPrice(price);
            tempCourse.setCoin(enumCoin);
            tempCourse.setIsPublic(boolIspublic);
            tempCourse = courseService.save(tempCourse);
            return "redirect:/images/upload/courses*img";
        }
        return "redirect:/logout";
    }

    //translation endpint code: course11
    @GetMapping("/courses/img/{imgId}")
    public String coursesUploadImg( Model model, 
            @PathVariable long imgId) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_course11_title));
            //add image to the temporal course
            if(imgId!=-1){
                tempCourse.setImg(imageService.load(imgId));
                tempCourse = courseService.save(tempCourse);
            }
            return "redirect:/courses/manage/videos";
        }
        return "redirect:/logout";
    }

    //translation endpint code: course12
    @GetMapping("/courses/manage/videos")
    public String coursesUploadVideo( Model model) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_course12_title));

            // model.addAttribute("videolist", courseService.listVideos(course));
            model.addAttribute("btnAddNewMsg", parent.getString(parent.MSG_course12_btnAddNewMsg));
            model.addAttribute("btnAddNewHref", "/videos/upload");
            model.addAttribute("btnNextMsg", parent.getString(parent.MSG_course12_btnNextMsg));
            model.addAttribute("btnNextHref", "/courses/sumary");
            System.out.println(tempCourse.getVideos());
            model.addAttribute("videolist", courseService.listVideos(tempCourse));

            model.addAttribute("videonameLbl", parent.getString(parent.MSG_course12_videonameLbl));
            model.addAttribute("addBtn", parent.getString(parent.MSG_course12_addBtn));
            model.addAttribute("nextBtn", parent.getString(parent.MSG_course12_nextBtn));
            
            return "course_manage_video";
        }
        return "redirect:/logout";
    }

    //translation endpint code: course13
    @GetMapping("/videos/upload")
    public String newVideo(Model model) {
        User user = (User) userService.findInUseUser();
        model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_course13_title));
        model.addAttribute("btnUploadMsg", parent.getString(parent.MSG_course13_btnUploadMsg));
        model.addAttribute("VideotitleLbl", parent.getString(parent.MSG_course13_VideotitleLbl));
        model.addAttribute("orderWarning", parent.getString(parent.MSG_course13_orderWarning));
        
        return "video_upload";
    }

    //translation endpint code: course14
    @PostMapping("/videos/upload")
    public String postNewVideo(Model model, 
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title) {
        User user = (User) userService.findInUseUser();
        model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_course14_title));
        
        String msg = parent.getString(parent.MSG_course14_msg1);
        boolean titleError=false;
        boolean videoError=true;
        String titleErrorMsg=parent.getString(parent.MSG_course14_titleErrorMsg);

        String classs ="badMsgDiv";
        if (title!=null && title.trim().length()>0) {
            if(!file.isEmpty()){
                try {
                    Video newVideo = videoService.saveNewVideo(title, file);
                    tempCourse.addVideo(newVideo);
                    tempCourse = courseService.save(tempCourse);
                    msg = parent.getString(parent.MSG_course14_msg2) + file.getOriginalFilename();
                    classs ="okMsgDiv";
                    videoError=false;
                } catch (Exception e) {
                    msg = parent.getString(parent.MSG_course14_msg3) + file.getOriginalFilename() + ". Error: " + e.getMessage();
                }
            }
        }

        if(!titleError && !videoError){
            return "redirect:/courses/manage/videos";
        }

        model.addAttribute("titleError", titleError);
        model.addAttribute("titleErrorMsg", titleErrorMsg);
        model.addAttribute("videoTitle", "");
        model.addAttribute("message", msg);
        model.addAttribute("classs", classs);
        model.addAttribute("btnUploadMsg", parent.getString(parent.MSG_course14_btnUploadMsg));
        
        model.addAttribute("VideotitleLbl", parent.getString(parent.MSG_course14_VideotitleLbl));
        model.addAttribute("orderWarning", parent.getString(parent.MSG_course14_orderWarning));

        return "video_upload";
    }

    //translation endpint code: course15
    @GetMapping("/courses/sumary")
    public String courseSumary( Model model ) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_course15_title));
            //add image to the temporal course
            System.out.println("image: " + tempCourse.getImg());

            model.addAttribute("img", tempCourse.getImg());
            model.addAttribute("course", tempCourse);

            model.addAttribute("courseTitleLbl", parent.getString(parent.MSG_course15_courseTitleLbl));
            model.addAttribute("DescriptionLbl", parent.getString(parent.MSG_course15_DescriptionLbl));
            model.addAttribute("PriceLbl", parent.getString(parent.MSG_course15_PriceLbl));
            model.addAttribute("coinLbl", parent.getString(parent.MSG_course15_coinLbl));
            model.addAttribute("publicLbl", parent.getString(parent.MSG_course15_publicLbl));
            model.addAttribute("VideoListLbl", parent.getString(parent.MSG_course15_VideoListLbl));
            
            model.addAttribute("btnNextMsg", parent.getString(parent.MSG_course15_btnNextMsg));
            return "course_sumary";
        }
        return "redirect:/logout";
    }

    //translation endpint code: course16
    @PostMapping("/courses/sumary")
    public String postCourseSumary(Model model) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model, parent.getString(parent.MSG_course16_title) );
            String msg = parent.getString(parent.MSG_course16_msg) ;
            
            tempCourse=null;
            model.addAttribute("msg", msg);
            return "confirmation_msg";
        }
        return "redirect:/logout";
    }
    

}

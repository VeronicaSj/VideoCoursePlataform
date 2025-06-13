package videocurseapp.demo.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import videocurseapp.demo.Repository.CourseRepository;
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

    //translation endpint code: course1
    @GetMapping("/courses")
    public String courses( Model model ) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_course1_title));
            
            //side bar options
            ArrayList<NavLink> sideBarLinks = new ArrayList<NavLink>();
            sideBarLinks.add(new NavLink("/courses/upload", parent.getString(parent.MSG_course1_uploadLink)));
            model.addAttribute("sideBarLinks", sideBarLinks);

            List<Course> courses = user.getCreatedCourses();
            model.addAttribute("courseList", courses);
            boolean thereAre = false;
            if (courses!=null && courses.size()>0) {
                thereAre = true;
            }
            model.addAttribute("thereAreCourses", thereAre);
            model.addAttribute("noCourseMsg", parent.getString(parent.MSG_course1_noCourseMsg));
            
            return "courses";
        }
        return "redirect:/logout";
    }

    //translation endpint code: course2
    @GetMapping("/courses/creator/{id}")
    public String coursesDetaill( Model model, 
            @PathVariable long id) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            Course course = courseService.find(id);
            model = parent.basicModelGenerator(user, model, parent.getString(parent.MSG_course2_title));
            if (course!=null) {
                model.addAttribute("title", course.getName());
                model.addAttribute("img", course.getImg());
                
                model.addAttribute("WatchBtn", parent.getString(parent.MSG_course2_WatchBtn));
                model.addAttribute("UpdateBtn", parent.getString(parent.MSG_course2_UpdateBtn));
                model.addAttribute("DeleteBtn", parent.getString(parent.MSG_course2_DeleteBtn));
                model.addAttribute("courseTitleLbl", parent.getString(parent.MSG_course2_courseTitleLbl));
                model.addAttribute("DescriptionLbl", parent.getString(parent.MSG_course2_DescriptionLbl));
                model.addAttribute("PriceLbl", parent.getString(parent.MSG_course2_PriceLbl));
                model.addAttribute("coinLbl", parent.getString(parent.MSG_course2_coinLbl));
                model.addAttribute("publicLbl", parent.getString(parent.MSG_course2_publicLbl));
                model.addAttribute("VideoListLbl", parent.getString(parent.MSG_course2_VideoListLbl));
            }

            model.addAttribute("course", course);
            
            model.addAttribute("btnNextMsg", "Update course");
            return "course_detaill_professor";
        }
        return "redirect:/logout";
    }

    //translation endpint code: course3
    @GetMapping("/courses/delete/{id}")
    public String coursesDelete( Model model, 
            @PathVariable long id) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            Course course = courseService.find(id);
            model = parent.basicModelGenerator(user, model, parent.getString(parent.MSG_course3_title));
            if (course!=null) {
                model.addAttribute("course", course);
                model.addAttribute("msg", parent.getString(parent.MSG_course3_msg));
                model.addAttribute("deleteBtn", parent.getString(parent.MSG_course3_deleteBtn));
                model.addAttribute("cancelBtn", parent.getString(parent.MSG_course3_cancelBtn));
            }
            return "course_delete";
        }
        return "redirect:/logout";
    }

    //translation endpint code: course4
    @PostMapping("/courses/delete/{id}")
    public String coursesDeletePOST( Model model, 
            @PathVariable long id) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            Course course = courseService.find(id);
            user.getCreatedCourses().remove(course);
            userService.update(user);
            return "redirect:/courses";
        }
        return "redirect:/logout";
    }

    //translation endpint code: course5
    @GetMapping("/courses/watch/{idcourse}")
    public String courseshelpDetaill( Model model, 
            @PathVariable long idcourse) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            Course course = courseService.find(idcourse);
            model = parent.basicModelGenerator(user, model, "Course not foud!");
            long firstVideoid=-1;
            if (course!=null) {
                model.addAttribute("title", course.getName());

                ArrayList<NavLink> sideBarLinks = new ArrayList<NavLink>();

                ArrayList<Video> videolist = courseService.listVideos(course);
                if(videolist!=null && videolist.size()>0){
                    for (Video v : videolist) {
                        sideBarLinks.add(new NavLink("/courses/watch/"+idcourse+"/"+v.getId(), v.getTitle()));
                    }
                    Video firstVideo = videolist.getFirst();
                    firstVideoid=firstVideo.getId();
                }
                
                model.addAttribute("sideBarLinks", sideBarLinks);
            }

            model.addAttribute("course", course);
            return "redirect:/courses/watch/"+idcourse+"/"+firstVideoid;
            
        }
        return "redirect:/logout";
    }

    //translation endpint code: course6
    @GetMapping("/courses/watch/{idcourse}/{idvideo}")
    public String courseshelpDetaill( Model model, 
            @PathVariable long idcourse,
            @PathVariable long idvideo) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            Course course = courseService.find(idcourse);
            boolean thereAreVideos= false;
            model = parent.basicModelGenerator(user, model, parent.getString(parent.MSG_course6_title));
            if (course!=null) {
                model.addAttribute("title", course.getName());
                
                ArrayList<NavLink> sideBarLinks = new ArrayList<NavLink>();

                ArrayList<Video> videolist = courseService.listVideos(course);
                if(videolist!=null && videolist.size()>0){
                    thereAreVideos=true;
                }
                for (Video v : videolist) {
                    sideBarLinks.add(new NavLink("/courses/watch/"+idcourse+"/"+v.getId(), v.getTitle()));
                }
                model.addAttribute("sideBarLinks", sideBarLinks);
            }
            
            model.addAttribute("videosrc", "/video/"+idvideo);
            
            model.addAttribute("thereAreVideos", thereAreVideos);

            model.addAttribute("sidebartitle", parent.getString(parent.MSG_course6_sidebartitle));
            model.addAttribute("sidebarNoVideo", parent.getString(parent.MSG_course6_sidebarNoVideo));
            model.addAttribute("noVideo", parent.getString(parent.MSG_course6_noVideo));
            
            return "course_watch";
        }
        return "redirect:/logout";
    }
}

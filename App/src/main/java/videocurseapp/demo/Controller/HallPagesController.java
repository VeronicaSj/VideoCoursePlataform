package videocurseapp.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import videocurseapp.demo.Repository.UserRepository;
import videocurseapp.demo.Service.UserService;
import videocurseapp.demo.Utilities.NavLinkGetter;


@Controller
public class HallPagesController {

    static final NavLinkGetter NAV_LINK_GETTER= new NavLinkGetter();
    private final UserService userService;

    @Autowired
    UserRepository userRepo;

    HallPagesController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String index( Model model ) {
        model.addAttribute("navLinkList", NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", "AcadeMice");
        model.addAttribute("subtitle", "Best teaching and learning plataform");
        model.addAttribute("description", "The best platform for online courses for people passionate about education");
                        
        return "index";
    }

    @GetMapping("/login")
    public String logIn( Model model) {
        model.addAttribute("navLinkList", NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", "Log In");
        model.addAttribute("subtitle", "Log In | AcadeMice");
        model.addAttribute("description", "The best platform for online courses for people passionate about education");
        model.addAttribute("error", false);
        model.addAttribute("errorMsg", "Invalid username and password. Try again");

        return "login";
    }

    @GetMapping("/login/error")
    public String logInError( Model model) {
        model.addAttribute("navLinkList", NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", "Log In");
        model.addAttribute("subtitle", "Log In | AcadeMice");
        model.addAttribute("description", "The best platform for online courses for people passionate about education");
        model.addAttribute("error", true);
        model.addAttribute("errorMsg", "Invalid username and password. Try again");

        return "login";
    }

   

   @GetMapping("/singup")
    public String singUp(Model model){
        model.addAttribute("navLinkList", NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", "Sing Up");
        model.addAttribute("subtitle", "Sing Up | AcadeMice");
        model.addAttribute("description", "The best platform for online courses for people passionate about education");
                        
        return "singup";
    }

    @PostMapping("/singup")
    public String postSingUp(@RequestParam("username") String username, @RequestParam("password") String password){
        //TODO: https://www.baeldung.com/registration-with-spring-mvc-and-spring-security
        userService.create(username, password);
       return  "redirect:/home";
    }

    @GetMapping("/logout")
    public String logOut(Model model){
        return "redirect:/";
    }

    @GetMapping("/about")
    public String about( Model model ) {
        
        model.addAttribute("navLinkList", NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", "About");
        model.addAttribute("subtitle", "About | AcadeMice");
        model.addAttribute("description", "The best platform for online courses for people passionate about education");
                        
        return "about";
    }
}
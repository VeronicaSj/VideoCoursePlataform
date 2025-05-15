package videocurseapp.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import videocurseapp.demo.Repository.UserRepository;
import videocurseapp.demo.Service.UserService;
import videocurseapp.demo.Utilities.EmailValidator;
import videocurseapp.demo.Utilities.NavLinkGetter;
import videocurseapp.demo.Utilities.UserDto;


@Controller
public class HallPagesController {

    static final NavLinkGetter NAV_LINK_GETTER= new NavLinkGetter();
    private final UserService userService;

    @Autowired
    UserRepository userRepo;
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    HallPagesController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String index( Model model ) {
        model.addAttribute("navLinkList", NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", "AcadeMice");
        model.addAttribute("subtitle", "Best teaching and learning plataform");
        model.addAttribute("description", "The best online course platform for people passionate about education");
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
        model.addAttribute("username", "");
        model.addAttribute("email", "");
        model.addAttribute("errorEmail", false);
        model.addAttribute("errorPwMatch", false);
        return "singup";
    }
    
    @GetMapping("/singup?username={username}&errorEmail={errorEmail}&email={email}&errorPwMatch={errorPwMatch}")
    public String singUpParm(Model model, 
            @PathVariable String username, 
            @PathVariable boolean errorEmail,
            @PathVariable String email,
            @PathVariable boolean errorPwMatch){
        model.addAttribute("navLinkList", NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", "Sing Up");
        model.addAttribute("subtitle", "Sing Up | AcadeMice");
        model.addAttribute("description", "The best platform for online courses for people passionate about education");
        System.out.println("sout" );
        model.addAttribute("username", username);
        model.addAttribute("email", email);
        model.addAttribute("errorEmail", true);
        model.addAttribute("errorEmailMsg", "ERROR!: Invalid mail");
        model.addAttribute("errorPwMatch", errorPwMatch);
        model.addAttribute("errorPwMatchMsg", "ERROR!: Unmatching password");
        
        return "singup";
    }

    @PostMapping("/singup")
    public String postSingUp(RedirectAttributes redirectAttributes, 
            @RequestParam("username") String username, 
            @RequestParam("email") String email, 
            @RequestParam("password") String password,  
            @RequestParam("matchingPassword") String matchingPassword){
        //TODO: https://www.baeldung.com/registration-with-spring-mvc-and-spring-security
        boolean validMail=new EmailValidator().isValid(email);
        boolean pwMatch= password.equals(matchingPassword);

        if( !validMail || !pwMatch){
            redirectAttributes.addAttribute("username", username);
            redirectAttributes.addAttribute("email", email);
            redirectAttributes.addAttribute("errorEmail", !validMail);
            redirectAttributes.addAttribute("errorPwMatch", !pwMatch);
            return  "redirect:/singup";
        }
        
        userService.create(username, password);

        return  "redirect:/home";
    }

    @GetMapping("/logout")
    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        // .. perform logout
        this.logoutHandler.logout(request, response, authentication);
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
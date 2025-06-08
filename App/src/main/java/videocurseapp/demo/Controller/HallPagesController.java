package videocurseapp.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import videocurseapp.demo.Service.UserService;
import videocurseapp.demo.Utilities.EmailValidator;


@Controller
public class HallPagesController {
    @Autowired
    private UserService userService;
    ControllerStaticParent parent = new ControllerStaticParent();

    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();


    @GetMapping("/")
    public String index( Model model ) {
        model.addAttribute("navLinkList", parent.NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", "AcadeMice");
        model.addAttribute("subtitle", "Best teaching and learning plataform");
        model.addAttribute("description", "The best online course platform for people passionate about education");
        return "index";
    }

    @GetMapping("/login")
    public String logIn( Model model) {
        
        model.addAttribute("navLinkList", parent.NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", "Log In");
        model.addAttribute("subtitle", "Log In | AcadeMice");
        model.addAttribute("description", "The best platform for online courses for people passionate about education");
        model.addAttribute("error", false);
        model.addAttribute("errorMsg", "Invalid username and password. Try again");

        return "login";
    }

    @GetMapping("/login/error")
    public String logInError( Model model) {
        model.addAttribute("navLinkList", parent.NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", "Log In");
        model.addAttribute("subtitle", "Log In | AcadeMice");
        model.addAttribute("description", "The best platform for online courses for people passionate about education");
        model.addAttribute("error", true);
        model.addAttribute("errorMsg", "Invalid username and password. Try again");

        return "login";
    }

   

    @GetMapping("/signup")
    public String signUp(Model model){
        model.addAttribute("navLinkList", parent.NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", "Sign Up");
        model.addAttribute("subtitle", "Sing Up | AcadeMice");
        model.addAttribute("description", "The best platform for online courses for people passionate about education");
        model.addAttribute("username", "");
        model.addAttribute("email", "");
        model.addAttribute("usernameInUseError", false);
        model.addAttribute("errorEmail", false);
        model.addAttribute("errorPwMatch", false);

        return "signup";
    }
    
    
    @GetMapping("/signup/{usernameInUseError}/{username}/{errorEmail}/{email}/{errorPwMatch}")
    public String signUpParm(Model model, 
            @PathVariable boolean usernameInUseError, 
            @PathVariable String username, 
            @PathVariable boolean errorEmail,
            @PathVariable String email,
            @PathVariable boolean errorPwMatch){
        model.addAttribute("navLinkList", parent.NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", "Sign Up");
        model.addAttribute("subtitle", "Sing Up | AcadeMice");
        model.addAttribute("description", "The best platform for online courses for people passionate about education");
        
        model.addAttribute("username", username);
        model.addAttribute("email", email);

        model.addAttribute("usernameInUseError", usernameInUseError);
        model.addAttribute("usernameInUseErrorMsg", "ERROR!: Username already in use");
        model.addAttribute("errorEmail", errorEmail);
        model.addAttribute("errorEmailMsg", "ERROR!: Invalid mail");
        model.addAttribute("errorPwMatch", errorPwMatch);
        model.addAttribute("errorPwMatchMsg", "ERROR!: Unmatching password");

        return "signup";
    }

    @PostMapping("/signup")
    public String postSignUp(
            @RequestParam("username") String username, 
            @RequestParam("email") String email, 
            @RequestParam("password") String password,  
            @RequestParam("matchingPassword") String matchingPassword){
        
        boolean usernameFree = true;
        try{
            UserDetails user = userService.loadUserByUsername(username);
            if(user!=null && user.getUsername()!=null && user.getUsername().equals(username)){
                usernameFree = false;
            }
        }catch(UsernameNotFoundException e){
            usernameFree = true;
        }
        
        boolean validMail=new EmailValidator().isValid(email);
        boolean pwMatch= password.equals(matchingPassword);

        if(!usernameFree || !validMail || !pwMatch){
            return  "redirect:/signup/"+!usernameFree+"/"+username+"/"+!validMail+"/"+email+"/"+!pwMatch;
        }
        
        userService.create(username, password, email);

        return  "redirect:/login";
    }

    @GetMapping("/logout")
    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        this.logoutHandler.logout(request, response, authentication);
        return "redirect:/";
    }

    @GetMapping("/about")
    public String about( Model model ) {
        
        model.addAttribute("navLinkList", parent.NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", "About");
        model.addAttribute("subtitle", "About | AcadeMice");
        model.addAttribute("description", "The best platform for online courses for people passionate about education");
                        
        return "about";
    }
}
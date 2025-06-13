package videocurseapp.demo.Controller;

import java.util.ArrayList;

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
        model.addAttribute("title", parent.getString(parent.MSG_HALL_INDEX_TITLE));
        model.addAttribute("subtitle", parent.getString(parent.MSG_HALL_INDEX_SUBTITLE));
        model.addAttribute("description", parent.getString(parent.MSG_HALL_INDEX_DESCRIPTION));
        model.addAttribute("startBtn", parent.getString(parent.MSG_HALL_INDEX_START_BTN));
        return "index";
    }

    //translation endpint code: hall1
    @GetMapping("/login")
    public String logIn( Model model) {
        model.addAttribute("navLinkList", parent.NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", parent.getString(parent.MSG_hall1_title));
        model.addAttribute("subtitle", parent.getString(parent.MSG_hall1_subtitle));
        model.addAttribute("description", parent.getString(parent.MSG_hall1_description));
        model.addAttribute("error", false);
        model.addAttribute("errorMsg", parent.getString(parent.MSG_hall1_errorMsg));

        model.addAttribute("userLbl", parent.getString(parent.MSG_hall1_userLbl));
        model.addAttribute("pwLbl", parent.getString(parent.MSG_hall1_pwLbl));
        model.addAttribute("submitBtn", parent.getString(parent.MSG_hall1_submitBtn));
        return "login";
    }

    //translation endpint code: hall2
    @GetMapping("/login/error")
    public String logInError( Model model) {
        model.addAttribute("navLinkList", parent.NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", parent.getString(parent.MSG_hall1_title));
        model.addAttribute("subtitle", parent.getString(parent.MSG_hall1_subtitle));
        model.addAttribute("description", parent.getString(parent.MSG_hall1_description));
        model.addAttribute("error", true);
        model.addAttribute("errorMsg", parent.getString(parent.MSG_hall1_errorMsg));

        model.addAttribute("userLbl", parent.getString(parent.MSG_hall1_userLbl));
        model.addAttribute("pwLbl", parent.getString(parent.MSG_hall1_pwLbl));
        model.addAttribute("submitBtn", parent.getString(parent.MSG_hall1_submitBtn));

        return "login";
    }

   

    //translation endpint code: hall3
    @GetMapping("/signup")
    public String signUp(Model model){
        model.addAttribute("navLinkList", parent.NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", parent.getString(parent.MSG_hall3_title));
        model.addAttribute("subtitle", parent.getString(parent.MSG_hall3_subtitle));
        model.addAttribute("description", parent.getString(parent.MSG_hall3_description));
        model.addAttribute("username", "");
        model.addAttribute("email", "");
        model.addAttribute("usernameInUseError", false);
        model.addAttribute("errorEmail", false);
        model.addAttribute("errorPwMatch", false);
        model.addAttribute("langValueES",false );
        model.addAttribute("langValueEN", true);

        
        model.addAttribute("langLbl", parent.getString(parent.MSG_hall3_langLbl));
        model.addAttribute("Userlbl", parent.getString(parent.MSG_hall3_Userlbl));
        model.addAttribute("Emaillbl", parent.getString(parent.MSG_hall3_Emaillbl));
        model.addAttribute("PassWordlbl", parent.getString(parent.MSG_hall3_PassWordlbl));
        model.addAttribute("Matchinglbl", parent.getString(parent.MSG_hall3_Matchinglbl));
        model.addAttribute("submitbtn", parent.getString(parent.MSG_hall3_submitbtn));
        
        return "signup";
    }
    
    //translation endpint code: hall4
    @GetMapping("/signup/{usernameInUseError}/{username}/{errorEmail}/{email}/{errorPwMatch}")
    public String signUpParm(Model model, 
            @PathVariable boolean usernameInUseError, 
            @PathVariable String username, 
            @PathVariable boolean errorEmail,
            @PathVariable String email,
            @PathVariable boolean errorPwMatch){
        model.addAttribute("navLinkList", parent.NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", parent.getString(parent.MSG_hall3_title));
        model.addAttribute("subtitle", parent.getString(parent.MSG_hall3_subtitle));
        model.addAttribute("description", parent.getString(parent.MSG_hall3_description));

        model.addAttribute("username", username);
        model.addAttribute("email", email);

        model.addAttribute("usernameInUseError", usernameInUseError);
        model.addAttribute("usernameInUseErrorMsg", parent.getString(parent.MSG_hall4_usernameInUseErrorMsg));
        model.addAttribute("errorEmail", errorEmail);
        model.addAttribute("errorEmailMsg", parent.getString(parent.MSG_hall4_errorEmailMsg));
        model.addAttribute("errorPwMatch", errorPwMatch);
        model.addAttribute("errorPwMatchMsg", "ERROR!: Unmatching password");
        
        model.addAttribute("langValueES",false );
        model.addAttribute("langValueEN", true);

        model.addAttribute("langLbl", parent.getString(parent.MSG_hall3_langLbl));
        model.addAttribute("Userlbl", parent.getString(parent.MSG_hall3_Userlbl));
        model.addAttribute("Emaillbl", parent.getString(parent.MSG_hall3_Emaillbl));
        model.addAttribute("PassWordlbl", parent.getString(parent.MSG_hall3_PassWordlbl));
        model.addAttribute("Matchinglbl", parent.getString(parent.MSG_hall3_Matchinglbl));
        model.addAttribute("submitbtn", parent.getString(parent.MSG_hall3_submitbtn));

        return "signup";
    }

    //translation endpint code: hall5
    @PostMapping("/signup")
    public String postSignUp(
            @RequestParam("username") String username, 
            @RequestParam("email") String email, 
            @RequestParam("password") String password,  
            @RequestParam("matchingPassword") String matchingPassword,
            @RequestParam("lang") String lang ){
        
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
        
        userService.create(username, password, email, lang);

        return  "redirect:/login";
    }

    @GetMapping("/logout")
    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        this.logoutHandler.logout(request, response, authentication);
        return "redirect:/";
    }

    //translation endpint code: hall6
    @GetMapping("/about")
    public String about( Model model ) {
        
        model.addAttribute("navLinkList", parent.NAV_LINK_GETTER.getHallNavLinkList());
        model.addAttribute("title", parent.getString(parent.MSG_hall6_title));
        model.addAttribute("subtitle", parent.getString(parent.MSG_hall6_subtitle));
        model.addAttribute("description", parent.getString(parent.MSG_hall6_description));
        
        ArrayList<String> msgList=new ArrayList<String>();
        msgList.add(parent.getString(parent.MSG_hall6_msg1));
        msgList.add(parent.getString(parent.MSG_hall6_msg2));
        msgList.add(parent.getString(parent.MSG_hall6_msg3));
        msgList.add(parent.getString(parent.MSG_hall6_msg4));
        model.addAttribute("msgList", msgList);

        model.addAttribute("startBtn", parent.getString(parent.MSG_hall6_startBtn));

        return "about";
    }
}
package videocurseapp.demo.Controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import videocurseapp.demo.Utilities.NavLink;

@Controller
public class HallPagesController {

    private static ArrayList<NavLink> getNavLinkList(){
        ArrayList<NavLink> res = new ArrayList<NavLink>();
        res.add(new NavLink("/login", "LogIn"));
        res.add(new NavLink("/singup","SingUp"));
        res.add(new NavLink("/about","About"));
        return res;
    }

    @GetMapping("/")
    public String index( Model model ) {
        model.addAttribute("navLinkList", getNavLinkList());
        model.addAttribute("title", "AcadeMice");
        model.addAttribute("subtitle", "Best teaching and learning plataform");
        model.addAttribute("description", "The best platform for online courses for people passionate about education");
                        
        return "index";
    }

    @GetMapping("/login")
    public String logIn( Model model ) {
        
        model.addAttribute("navLinkList", getNavLinkList());
        model.addAttribute("title", "Log In");
        model.addAttribute("subtitle", "Log In | AcadeMice");
        model.addAttribute("description", "The best platform for online courses for people passionate about education");
                        
        return "login";
    }

    @GetMapping("/submitLogin")
    public String submitLogIn( Model model ) {
        String errorMsg= "default errorMsg";

        model.addAttribute("navLinkList", getNavLinkList());
        model.addAttribute("title", "Log In");
        model.addAttribute("subtitle", "Log In | AcadeMice");
        model.addAttribute("error", true);
        model.addAttribute("errorMsg", errorMsg);
                        
        return "login";
    }

    @GetMapping("/singup")
    public String singUp( Model model ) {
        
        model.addAttribute("navLinkList", getNavLinkList());
        model.addAttribute("title", "Sing Up");
        model.addAttribute("subtitle", "Sing Up | AcadeMice");
        model.addAttribute("description", "The best platform for online courses for people passionate about education");
                        
        return "singup";
    }

    @GetMapping("/about")
    public String help( Model model ) {
        
        model.addAttribute("navLinkList", getNavLinkList());
        model.addAttribute("title", "About");
        model.addAttribute("subtitle", "About | AcadeMice");
        model.addAttribute("description", "The best platform for online courses for people passionate about education");
                        
        return "about";
    }
    

}
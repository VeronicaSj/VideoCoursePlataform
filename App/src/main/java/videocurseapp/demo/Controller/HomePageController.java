package videocurseapp.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import videocurseapp.demo.Utilities.NavLinkGetter;

@Controller
public class HomePageController {

    static final NavLinkGetter NAV_LINK_GETTER= new NavLinkGetter();

    @GetMapping("/home")
    public String home( Model model ) {
        
        model.addAttribute("navLinkList", NAV_LINK_GETTER.getInAppNavLinkList());
        model.addAttribute("title", "Home");
        model.addAttribute("subtitle", "Home | AcadeMice");
        
        return "home";
    }
}

package videocurseapp.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import videocurseapp.demo.Utilities.NavLinkGetter;

@Controller
public class HelpPageController {
    static final NavLinkGetter NAV_LINK_GETTER= new NavLinkGetter();

    @GetMapping("/help")
    public String account( Model model ) {
        
        model.addAttribute("navLinkList", NAV_LINK_GETTER.getInAppNavLinkList());
        model.addAttribute("avatar", "/resources/avatar.png");
        model.addAttribute("title", "Help");
        model.addAttribute("subtitle", "Help | AcadeMice");
        
        return "help";
    }
}

package videocurseapp.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import videocurseapp.demo.Utilities.NavLinkGetter;

@Controller
public class AccountPageController {
    static final NavLinkGetter NAV_LINK_GETTER= new NavLinkGetter();

    @GetMapping("/account")
    public String account( Model model ) {
        
        model.addAttribute("navLinkList", NAV_LINK_GETTER.getInAppNavLinkList());
        model.addAttribute("avatar", "/resources/avatar.png");
        model.addAttribute("title", "My Account");
        model.addAttribute("subtitle", "My Account | AcadeMice");
                        
        return "account";
    }
}

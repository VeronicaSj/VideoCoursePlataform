package videocurseapp.demo.Controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import videocurseapp.demo.Model.Image;
import videocurseapp.demo.Model.User;
import videocurseapp.demo.Utilities.NavLink;
import videocurseapp.demo.Utilities.NavLinkGetter;

@Controller
public class ControllerStaticParent {
    public static final NavLinkGetter NAV_LINK_GETTER= new NavLinkGetter();
    public static boolean isInit = false;
    
    public static Model generalModel=null;


    public static Model basicModelGenerator(User user, Model model, String title) {
        ArrayList<NavLink> linklist = NAV_LINK_GETTER.getInAppNavLinkList();
        if(user.isProfessor()){
            linklist.add(new NavLink("/courses", "My courses"));
        }
        model.addAttribute("navLinkList", linklist);
        
        
        if(user!=null){
            model.addAttribute("avatar", user.getAvatar());
        }
        
        model.addAttribute("title", title);
        model.addAttribute("defaultImg", Image.DEFAULT_IMG);
        model.addAttribute("subtitle", title + " | AcadeMice");
        return model;
    }



}

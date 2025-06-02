package videocurseapp.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import videocurseapp.demo.Model.User;
import videocurseapp.demo.Service.ImageService;
import videocurseapp.demo.Service.UserService;

@Controller
public class HomePageController {

    @Autowired
    private ImageService filesStorageService;

    @Autowired
    private UserService userService;
    private ControllerStaticParent parent = new ControllerStaticParent();

    @GetMapping("/home")
    public String home( Model model ) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  "Home");
        
        return "home";
        }
        return "redirect:/logout";
    }

    
}

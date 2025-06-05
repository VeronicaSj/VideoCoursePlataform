package videocurseapp.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import videocurseapp.demo.Model.User;
import videocurseapp.demo.Service.UserService;

@Controller
public class HelpPageController {

    @Autowired
    private UserService userService;
    private ControllerStaticParent parent = new ControllerStaticParent();

    @GetMapping("/help")
    public String account( Model model ) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  "Help");

            return "help";
        }
        return "redirect:/logout";
    }
}

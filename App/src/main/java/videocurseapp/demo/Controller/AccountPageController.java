package videocurseapp.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import videocurseapp.demo.Model.User;
import videocurseapp.demo.Service.ImageService;
import videocurseapp.demo.Service.UserService;
import videocurseapp.demo.Utilities.EmailValidator;
import videocurseapp.demo.Utilities.NavLink;

@Controller
public class AccountPageController {

    @Autowired
    private ImageService filesStorageService;

    @Autowired
    private UserService userService;
    private ControllerStaticParent parent = new ControllerStaticParent();

    
    @GetMapping("/account")
    public String account( Model model ) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model,  "My Account");
            ArrayList<NavLink> sideBarLinks = new ArrayList<NavLink>();
            sideBarLinks.add(new NavLink("/account/edit/"+user.getAvatar().getId(), "Edit Account"));
		    sideBarLinks.add(new NavLink("/account/changepw", "Change Password"));
			sideBarLinks.add(new NavLink("/logout", "Log Out"));
			sideBarLinks.add(new NavLink("/account/delete", "Delete Account"));
            if(!user.isProfessor()){
                sideBarLinks.add(new NavLink("/account/switch", "Permanentelly switch to Professor"));
            }
			
            model.addAttribute("sideBarLinks", sideBarLinks);

            //account data
            model.addAttribute("username","Username: " + user.getUsername());
            model.addAttribute("email", "E-mail: "+user.getEmail());
            model.addAttribute("avatar", user.getAvatar());
            model.addAttribute("isProfessor", user.isProfessor());
            model.addAttribute("isProfessorMsg", "Professor account");
            model.addAttribute("uploadCurseMsg", "Upload a curse");


        return "account";
        }
        return "redirect:/logout";
    }

    @GetMapping("/account/edit/{pvImgId}")
    public String accountEdit( Model model,
            @PathVariable long pvImgId ) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model, "Edit Account");
            model.addAttribute("redirect", "account*edit");
            model.addAttribute("imgId", pvImgId);
            String msg = "The image could not be uploaded";
            String classs ="badMsgDiv";
            if(pvImgId>-1){
                msg = "The image was succesfully uploaded";
                classs ="okMsgDiv";
            }
            model.addAttribute("message", msg);
            model.addAttribute("classs", classs);
        return "account_edit";
        }
        return "redirect:/logout";
    }

    @PostMapping("/account/edit/{pvImgId}")
    public String postAccountEdit( Model model,
            @RequestParam("imgId") long rqImgId,
            @RequestParam("email") String email,
            @PathVariable long pvImgId) {
        User user = userService.findInUseUser();
        if(user != null){
            
            model = parent.basicModelGenerator(user, model, "Edit Account");

            String msg = "Your acount could not be updated";

            if (!email.isEmpty() || new EmailValidator().isValid(email) || rqImgId>-1) {
                    if (!email.isEmpty()){
                        if(new EmailValidator().isValid(email)){
                            user.setEmail(email);
                        }else{
                            msg = msg + ". Your new mail was not valid";
                        }
                    }
                if (rqImgId>-1){
                    user.setAvatar(filesStorageService.load(rqImgId));
                }
                if (userService.update(user)) {
                    msg="Your account was succesfully changed";
                }
            }
            model.addAttribute("msg", msg);
            
        return "account_change_confirmation";
        }
        return "redirect:/logout";
    }

    @GetMapping("/account/changepw")
    public String accountChangePw( Model model ) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model, "Change Account Password");
            
            
            model.addAttribute("oldPwLbl", "Old PassWord: ");
            model.addAttribute("newPwLbl", "New PassWord: ");
            model.addAttribute("mtchPwLbl", "Matching New PassWord: ");
            model.addAttribute("submitBtn", "Change PassWord");

            
            return "account_change_pw";
        }
        return "redirect:/logout";
    }

    @PostMapping("/account/changepw")
    public String postAccountChangePw( Model model,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("matchingPassword") String matchingPassword) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model, "Change Account Password");

            String msg = "Your pasword could not be changed";

            if (newPassword.equals(matchingPassword)) {
                user.setPassword(newPassword);
                if (userService.changePw(user)) {
                    msg="Your pasword was succesfully changed";
                }
            }else{
                msg = msg +  ", your new password didn't match the retype";
            }

            model.addAttribute("msg", msg);

            return "account_change_confirmation";
        }
        return "redirect:/logout";
    }

    @GetMapping("/account/switch")
    public String postAccountChangePw( Model model) {
        User user = userService.findInUseUser();
        if(user != null){
            if (!user.isProfessor()) {
                user.setProfessor(true);
                userService.update(user);
                
                model.addAttribute("navLinkList", parent.NAV_LINK_GETTER.getInAppNavLinkList());
                model.addAttribute("msg", "You have changed your profile from Student to Professor");
                return "account_change_confirmation";
            }
            return "redirect:/account";
            
        }
        return "redirect:/logout";
    }

    @GetMapping("/account/delete")
    public String accountDelete( Model model ) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model, "Account Delete");
            
            model.addAttribute("deleteAccountWarningMsg", "If you delete your account you " +
                    "will lose all the related data. Are you sure you want to delete all your info?");
            model.addAttribute("aceptBtnMsg", "Delete my account");
            model.addAttribute("cancelBtnMsg", "Cancel");
            
            return "account_delete";
        }
        return "redirect:/logout";
    }

    @GetMapping("/account/delete/confirm")
    public String accountDeleteConfirm( Model model ) {
        User user = userService.findInUseUser();

        if(userService.delete(user) == false){
            model.addAttribute("navLinkList", parent.NAV_LINK_GETTER.getHallNavLinkList());

            model.addAttribute("msg", "Your user could not be deleted");
            return "account_change_confirmation";
        }
        return "redirect:/logout";
    }
}

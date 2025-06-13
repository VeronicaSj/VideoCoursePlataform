package videocurseapp.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import videocurseapp.demo.Model.User;
import videocurseapp.demo.Service.ImageService;
import videocurseapp.demo.Service.UserService;
import videocurseapp.demo.Utilities.EmailValidator;
import videocurseapp.demo.Utilities.NavLink;

@Controller
public class AccountPageController {

    @Autowired
    private ImageService imgService;

    @Autowired
    private UserService userService;
    private ControllerStaticParent parent = new ControllerStaticParent();

    private User tempUser = new User();
    
    //translation endpint code: account1
    @GetMapping("/account")
    public String account( Model model ) {
        User user = (User) userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model, parent.getString(parent.MSG_account1_title));
            ArrayList<NavLink> sideBarLinks = new ArrayList<NavLink>();
            sideBarLinks.add(new NavLink("/account/edit", parent.getString(parent.MSG_account1_sidebarEdit)));
		    sideBarLinks.add(new NavLink("/account/changepw", parent.getString(parent.MSG_account1_sidebarPw)));
			sideBarLinks.add(new NavLink("/logout", parent.getString(parent.MSG_account1_sidebarLogOut)));
			sideBarLinks.add(new NavLink("/account/delete", parent.getString(parent.MSG_account1_sidebarDelete)));
            if(!user.isProfessor()){
                sideBarLinks.add(new NavLink("/account/switch", parent.getString(parent.MSG_account1_sidebarSwitch)));
            } else{
                sideBarLinks.add(new NavLink("/account/update/bank", parent.getString(parent.MSG_account1_sidebarBank)));
            }
            model.addAttribute("sideBarLinks", sideBarLinks);

            //account data
            model.addAttribute("username","Username: " + user.getUsername());
            model.addAttribute("email", "E-mail: "+user.getEmail());
            model.addAttribute("avatar", user.getAvatar());
            model.addAttribute("isProfessor", user.isProfessor());
            model.addAttribute("isProfessorMsg", parent.getString(parent.MSG_account1_isProfessorMsg));
            model.addAttribute("uploadCurseMsg", parent.getString(parent.MSG_account1_uploadCurseMsg));


        return "account";
        }
        return "redirect:/logout";
    }

    //translation endpint code: account2
    @GetMapping("/account/edit")
    public String accountEdit( Model model) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model, parent.getString(parent.MSG_account2_title));
            model.addAttribute("redirect", "account*edit");
            model.addAttribute("message", null);
            model.addAttribute("classs", null);
            
            model.addAttribute("langLbl", parent.getString(parent.MSG_account2_langLbl));
            model.addAttribute("langValueES", user.getLang().equals("Español"));
            model.addAttribute("langValueEN", user.getLang().equals("English"));
            
            model.addAttribute("avatarLbl", parent.getString(parent.MSG_account2_avatarLbl));
            model.addAttribute("emailLbl", parent.getString(parent.MSG_account2_emailLbl));
            model.addAttribute("editBtn", parent.getString(parent.MSG_account2_editBtn));

            return "account_edit";
        }
        return "redirect:/logout";
    }

    
    //translation endpint code: account3
    @PostMapping("/account/edit")
    public String postAccountEdit( Model model,
            @RequestParam("file") MultipartFile file,
            @RequestParam("email") String email,
            @RequestParam("lang") String lang ) {
        User user = userService.findInUseUser();
        if(user != null){
            
            model = parent.basicModelGenerator(user, model, parent.getString(parent.MSG_account3_title));

            String msg = parent.getString(parent.MSG_account3_msg1);

            if (!email.isEmpty() || !file.isEmpty() || !lang.isEmpty()) {
                boolean error =false;
                if (!email.isEmpty()){
                    if(new EmailValidator().isValid(email)){
                        user.setEmail(email);
                    }else{
                        error=true;
                        msg = msg + parent.getString(parent.MSG_account3_msg2);
                    }
                }
                if (!file.isEmpty()){
                    try {
                        user.setAvatar(imgService.save(file));
                    } catch (Exception e) {
                        error=true;
                        msg = msg + parent.getString(parent.MSG_account3_msg3) + file.getOriginalFilename() + ". Error: " + e.getMessage();
                    }
                }

                user.setLang(lang);

                if (!error && userService.update(user)) {
                    msg=parent.getString(parent.MSG_account3_msg4);
                }
            }
            model.addAttribute("msg", msg);
            
        return "account_change_confirmation";
        }
        return "redirect:/logout";
    }

    //translation endpint code: account4
    @GetMapping("/account/changepw")
    public String accountChangePw( Model model ) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model, parent.getString(parent.MSG_account4_title));
            
            
            model.addAttribute("oldPwLbl", parent.getString(parent.MSG_account4_oldPwLbl));
            model.addAttribute("newPwLbl", parent.getString(parent.MSG_account4_newPwLbl));
            model.addAttribute("mtchPwLbl", parent.getString(parent.MSG_account4_mtchPwLbl));
            model.addAttribute("submitBtn", parent.getString(parent.MSG_account4_submitBtn));

            
            return "account_change_pw";
        }
        return "redirect:/logout";
    }

    //translation endpint code: account5
    @PostMapping("/account/changepw")
    public String postAccountChangePw( Model model,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("matchingPassword") String matchingPassword) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model, parent.getString(parent.MSG_account5_title));

            String msg = parent.getString(parent.MSG_account5_msg1);

            if (newPassword.equals(matchingPassword)) {
                user.setPassword(newPassword);
                if (userService.changePw(user)) {
                    msg=parent.getString(parent.MSG_account5_msg2);
                }
            }else{
                msg = msg +  parent.getString(parent.MSG_account5_msg3);
            }

            model.addAttribute("msg", msg);

            return "account_change_confirmation";
        }
        return "redirect:/logout";
    }

    //translation endpint code: account6
    @GetMapping("/account/update/bank")
    public String accountUpdateBank( Model model) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model, parent.getString(parent.MSG_account6_title));
            model.addAttribute("subtitle", parent.getString(parent.MSG_account6_subtitle));
            model.addAttribute("errorTitular", false);
            model.addAttribute("errorIban", false);

            model.addAttribute("errorTitularMsg", "");
            model.addAttribute("errorIbanMsg", "");

            model.addAttribute("titular", user.getTitular());
            model.addAttribute("iban", user.getIban());

            model.addAttribute("btnmsg", parent.getString(parent.MSG_account6_btnmsg));
            model.addAttribute("href", "/account/update/bank");

            model.addAttribute("titularLbl", parent.getString(parent.MSG_account6_titularLbl));
            model.addAttribute("IBANLbl", parent.getString(parent.MSG_account6_IBANLbl));

            return "account_bank";
            
        }
        return "redirect:/logout";
    }

    //translation endpint code: account7
    @PostMapping("/account/update/bank")
    public String postAccountUpdateBank( Model model,
            @RequestParam("titular") String titular,
            @RequestParam("iban") String iban ) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model, parent.getString(parent.MSG_account7_title));
            model.addAttribute("subtitle", parent.getString(parent.MSG_account7_subtitle));
            model.addAttribute("errorTitular", false);
            model.addAttribute("errorIban", false);

            model.addAttribute("errorTitularMsg", "");
            model.addAttribute("errorIbanMsg", "");

            model.addAttribute("titular", "");
            model.addAttribute("iban", "");

            model.addAttribute("btnmsg", parent.getString(parent.MSG_account7_btnmsg));
            model.addAttribute("href", "/account/update/bank");
            String msg = parent.getString(parent.MSG_account7_msg1);

            if (!iban.isEmpty() && !titular.isEmpty() 
                    && iban.length()<35 && iban.length()>4) {
                user.setTitular(titular);
                user.setIban(iban);
                user.setProfessor(true);
                userService.update(user);
                
                model.addAttribute("navLinkList", parent.NAV_LINK_GETTER.getInAppNavLinkList());
                System.out.println(msg);
                msg =parent.getString(parent.MSG_account7_msg2);
            }
            System.out.println(msg);
            model.addAttribute("msg", msg);
            return "account_change_confirmation";
        }
        return "redirect:/logout";
    }

    //translation endpint code: account8
    @GetMapping("/account/switch")
    public String postAccountUpdateBank( Model model) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model, parent.getString(parent.MSG_account8_title));
            model.addAttribute("subtitle", parent.getString(parent.MSG_account8_subtitle));
            model.addAttribute("errorTitular", false);
            model.addAttribute("errorIban", false);

            model.addAttribute("errorTitularMsg", "");
            model.addAttribute("errorIbanMsg", "");

            model.addAttribute("titular", "");
            model.addAttribute("iban", "");

            model.addAttribute("btnmsg", parent.getString(parent.MSG_account8_btnmsg));
            model.addAttribute("href", "/account/switch");
            
            model.addAttribute("titularLbl", parent.getString(parent.MSG_account8_titularLbl));
            model.addAttribute("IBANLbl", parent.getString(parent.MSG_account8_IBANLbl));
            return "account_bank";
            
        }
        return "redirect:/logout";
    }

    //translation endpint code: account9
    @PostMapping("/account/switch")
    public String postAccountSwitch( Model model,
            @RequestParam("titular") String titular,
            @RequestParam("iban") String iban ) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model, parent.getString(parent.MSG_account9_title));
            model.addAttribute("subtitle", parent.getString(parent.MSG_account9_subtitle));
            model.addAttribute("errorTitular", false);
            model.addAttribute("errorIban", false);

            model.addAttribute("errorTitularMsg", "");
            model.addAttribute("errorIbanMsg", "");

            model.addAttribute("titular", "");
            model.addAttribute("iban", "");

            model.addAttribute("btnmsg", parent.getString(parent.MSG_account9_btnmsg));
            model.addAttribute("href", "/account/switch");

            String msg = parent.getString(parent.MSG_account9_msg1);

            if (!user.isProfessor() && !iban.isEmpty() && !titular.isEmpty() 
                    && iban.length()<35 && iban.length()>4) {
                user.setTitular(titular);
                user.setIban(iban);
                user.setProfessor(true);
                userService.update(user);
                
                model.addAttribute("navLinkList", parent.NAV_LINK_GETTER.getInAppNavLinkList());
                msg =parent.getString(parent.MSG_account9_msg2);
            }
            model.addAttribute("msg", msg);
            return "account_change_confirmation";
        }
        return "redirect:/logout";
    }

    //translation endpint code: account10
    @GetMapping("/account/delete")
    public String accountDelete( Model model ) {
        User user = userService.findInUseUser();
        if(user != null){
            model = parent.basicModelGenerator(user, model, parent.getString(parent.MSG_account10_title));
            
            model.addAttribute("deleteAccountWarningMsg", parent.getString(parent.MSG_account10_deleteAccountWarningMsg));
            model.addAttribute("deleteBtn", parent.getString(parent.MSG_account10_deleteBtn));
            model.addAttribute("cancelBtn", parent.getString(parent.MSG_account10_cancelBtn));
            return "account_delete";
        }
        return "redirect:/logout";
    }

    //translation endpint code: account11
    @GetMapping("/account/delete/confirm")
    public String accountDeleteConfirm( Model model ) {
        User user = userService.findInUseUser();

        if(userService.delete(user) == false){
            model.addAttribute("navLinkList", parent.NAV_LINK_GETTER.getHallNavLinkList());

            model.addAttribute("msg", parent.getString(parent.MSG_account11_msg));
            return "account_change_confirmation";
        }
        return "redirect:/logout";
    }
}

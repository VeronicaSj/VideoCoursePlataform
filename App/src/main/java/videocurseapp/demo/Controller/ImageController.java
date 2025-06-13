package videocurseapp.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import videocurseapp.demo.Model.User;
import videocurseapp.demo.Service.ImageService;
import videocurseapp.demo.Service.UserService;


@Controller
public class ImageController {

  @Autowired
  ImageService storageService;
  @Autowired 
  UserService userService;

  private ControllerStaticParent parent = new ControllerStaticParent();

  //translation endpint code: image1
  @GetMapping("/images/upload/{redirect}")
  public String newImage(Model model,
      @PathVariable String redirect) {
    User user = (User) userService.findInUseUser();
    model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_image1_title1));
    long imgId = -1; 
    
    model.addAttribute("btnUploadMsg", parent.getString(parent.MSG_image1_btnUploadMsg));
    model.addAttribute("btnNextMsg", parent.getString(parent.MSG_image1_btnNextMsg));
    redirect = redirect.replace('*', '/');
    model.addAttribute("btnNextHref", "/" + redirect + "/" + imgId);

    return "image_upload";
  }

  //translation endpint code: image2
  @PostMapping("/images/upload/{redirect}")
  public String uploadImage(Model model, 
          @RequestParam("file") MultipartFile file,
          @PathVariable String redirect) {
      
      User user = (User) userService.findInUseUser();
      model = parent.basicModelGenerator(user, model,  parent.getString(parent.MSG_image2_title));

      long imgId = -1; 

      String msg = parent.getString(parent.MSG_image2_msg1);
      String classs ="badMsgDiv";
      if(!file.isEmpty()){
        model.addAttribute("imgId", imgId);
        try {
          imgId = storageService.save(file).getId();
          msg = parent.getString(parent.MSG_image2_msg2) + file.getOriginalFilename();
          classs ="okMsgDiv";
        } catch (Exception e) {
          msg = parent.getString(parent.MSG_image2_msg3)+ file.getOriginalFilename() + ". Error: " + e.getMessage();
        }
      }

        model.addAttribute("message", msg);
        model.addAttribute("classs", classs);
        model.addAttribute("btnUploadMsg", parent.getString(parent.MSG_image2_btnUploadMsg));
        model.addAttribute("btnNextMsg", parent.getString(parent.MSG_image2_btnNextMsg));
        redirect = redirect.replace('*', '/');
        model.addAttribute("btnNextHref", "/" + redirect + "/" + imgId);
        
        return "image_upload";
  }

}
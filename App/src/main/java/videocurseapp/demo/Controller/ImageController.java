package videocurseapp.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import videocurseapp.demo.Model.Image;
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

  @GetMapping("/images/upload/{redirect}")
  public String newImage(Model model,
      @PathVariable String redirect) {
    User user = (User) userService.findInUseUser();
    model = parent.basicModelGenerator(user, model,  "Upload Image");
    long imgId = -1; 
    
    model.addAttribute("btnUploadMsg", "Upload Image");
    model.addAttribute("btnNextMsg", "Next");
    redirect = redirect.replace('*', '/');
    model.addAttribute("btnNextHref", "/" + redirect + "/" + imgId);

    return "image_upload";
  }

  @PostMapping("/images/upload/{redirect}")
  public String uploadImage(Model model, 
          @RequestParam("file") MultipartFile file,
          @PathVariable String redirect) {
      
      User user = (User) userService.findInUseUser();
      model = parent.basicModelGenerator(user, model,  "Upload Image");

      long imgId = -1; 

      String msg = "You have to choose an image to upload";
      String classs ="badMsgDiv";
      if(!file.isEmpty()){
        model.addAttribute("imgId", imgId);
        try {
          imgId = storageService.save(file).getId();
          msg = "Uploaded the image successfully: " + file.getOriginalFilename();
          classs ="okMsgDiv";
        } catch (Exception e) {
          msg = "Could not upload the image: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
        }
      }

        model.addAttribute("message", msg);
        model.addAttribute("classs", classs);
        model.addAttribute("btnUploadMsg", "Upload Image");
        model.addAttribute("btnNextMsg", "Next");
        redirect = redirect.replace('*', '/');
        model.addAttribute("btnNextHref", "/" + redirect + "/" + imgId);
        
        return "image_upload";
  }

  @GetMapping("/images")
  public String getListImages(Model model) {
        User user = (User) userService.findInUseUser();
        model = parent.basicModelGenerator(user, model,  "Upload Course");

        List<Image> imageInfos = storageService.loadAll();
        

    model.addAttribute("images", imageInfos);
    return "images";
  }
}
package videocurseapp.demo.Controller;

import java.net.URI;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import videocurseapp.demo.Endpoints;
import videocurseapp.demo.Model.Image;
import videocurseapp.demo.Repository.ImageRepository;




//https://www.techgeeknext.com/spring-boot/spring-boot-upload-image#:~:text=In%20this%20tutorial%2C%20we%27ll%20show%20you%20how%20to,GET%2Fimage%20method%20with%20image%20name%2C%20can%20view%20image.
@RestController
@RequestMapping(Endpoints.EP_IMG_ROOT)
public class ImageController {
        @Autowired
        private ImageRepository imageRepo;
     
        @GetMapping(Endpoints.EP_USER_GET_ID)
        public ResponseEntity<Model> getById(@PathVariable Long id, Model model, Principal principal) {
        ResponseEntity<Model> res = ResponseEntity.notFound().build();
        Optional<Image> opt = imageRepo.findById(id);
        if(opt!=null  && opt.isPresent()){
                Image found = opt.get();
                if (found != null) {
                model.addAttribute("img", found);
                res= ResponseEntity.ok(model);
                }
        }
        return res;

        }
        @GetMapping(Endpoints.EP_IMG_GET_ID)
        public ResponseEntity<Model> getAll(Model model) {
                model.addAttribute("images", imageRepo.findAll());
                return ResponseEntity.ok(model);
        }

        @PostMapping(path=Endpoints.EP_IMG_POST, consumes = "application/json", produces = "application/json")
        public ResponseEntity<Image> createUser(@RequestBody Image img, UriComponentsBuilder ucb) {
                Image saved = imageRepo.save(img);
                URI locationOfNew = ucb
                        .path(Endpoints.EP_IMG_GET_ID)
                        .buildAndExpand(saved.getId())
                        .toUri();
                System.out.println(locationOfNew);
                return ResponseEntity.created(locationOfNew).build();
        }

        @PutMapping(path=Endpoints.EP_IMG_PUT)
        private ResponseEntity<Void> putUser(@PathVariable Long id, @RequestBody Image update, @RequestParam("image") MultipartFile file) {
                imageRepo.save(update);
                return ResponseEntity.noContent().build();
        }

        @DeleteMapping(path=Endpoints.EP_IMG_DELETE)
        private ResponseEntity<Void> deleteImg(@PathVariable Long id) {
                imageRepo.delete(new Image());
                return ResponseEntity.noContent().build();
        }

        
}
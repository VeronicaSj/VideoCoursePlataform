package videocurseapp.demo.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import videocurseapp.demo.Model.Image;
import videocurseapp.demo.Repository.ImageRepository;

@Service
public class ImageService {

  @Autowired
  private ImageRepository imageRepository;


  public Image save(MultipartFile imageFile) {
    Image res = null;
    try {
        byte[] img = imageFile.getBytes();
        Image imginfo = new Image("", img);
        res = imageRepository.save(imginfo);
          System.out.println("Image saved"+ res.getId());
    } catch (Exception e) {
          System.out.println( e.getMessage());
      throw new RuntimeException("Error: " + e.getMessage());
    }    
    return res;
  }

  public Image load(long id) {
    Image res = null;
    Optional<Image> opt = imageRepository.findById(id);
    if(opt.isPresent()){
      res = opt.get();
    }
    return res;
  }

  public List<Image> loadAll() {
      return (List) imageRepository.findAll();
  }
}
package com.example.apirest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.apirest.Repository.ImageRepository;

@RestController
public class ImageController {
    //https://medium.com/@Abhinav69/upload-and-download-images-with-rest-apis-spring-boot-3966c89102cd
    @Autowired
    ImageRepository imageRepository;

    /**
    @PostMapping("/upload/image")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file)
            throws IOException {

        imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes())).build());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }

    @GetMapping(path = {"/get/image/info/{id}"})
    public Image getImageDetails(@PathVariable("id") Integer id) throws IOException {

        final Optional<Image> dbImage = imageRepository.findById(id);

        return Image.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
    }

    @GetMapping(path = {"/get/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
    }
                */
}
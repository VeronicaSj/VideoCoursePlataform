package videocurseapp.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import videocurseapp.demo.Model.Image;
import videocurseapp.demo.Repository.CourseRepository;
import videocurseapp.demo.Repository.ImageRepository;
import videocurseapp.demo.Repository.UserRepository;
import videocurseapp.demo.Repository.VideoRepository;

@SpringBootApplication
public class VideoCurseWebApplication implements  CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(VideoCurseWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		pruebas();
	}

	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private  CourseRepository courseRepository;
	@Autowired
	private VideoRepository videoRepository;

	public void pruebas(){
		Image img = Image.DEFAULT_AVATAR;
		if(((ArrayList) imageRepository.findImageByName("DEFAULT_AVATAR")).size()!=0){
			imageRepository.save(img);
		}
		
	}
}

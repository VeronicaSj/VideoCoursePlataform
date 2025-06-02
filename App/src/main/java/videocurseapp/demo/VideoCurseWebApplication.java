package videocurseapp.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import videocurseapp.demo.Model.Image;
import videocurseapp.demo.Model.User;
import videocurseapp.demo.Repository.ImageRepository;
import videocurseapp.demo.Repository.UserRepository;

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

	public void pruebas(){
		Image img = Image.DEFAULT_AVATAR;
		if(((ArrayList) imageRepository.findImageByName("DEFAULT_AVATAR")).size()!=0){
			imageRepository.save(img);
		}
		User user = new User("n", new BCryptPasswordEncoder().encode("n"), "n@nm.nm");
		
		userRepository.save(user);
		System.out.println(userRepository.save(user));
	}
}

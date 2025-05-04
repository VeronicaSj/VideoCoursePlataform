package com.example.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.apirest.Model.User;
import com.example.apirest.Repository.UserRepository;

@SpringBootApplication
public class ApirestApplication implements  CommandLineRunner{
	/*Before runing :
	 * 	CREATE USER 'videocurse'@'localhost' IDENTIFIED BY 'admin';
	 * 	GRANT ALL PRIVILEGES ON *.* TO 'videocurse'@'localhost' WITH GRANT OPTION;
	 */

	 @Autowired
	 UserRepository userRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		pruebas();
	}


	public void pruebas(){
		System.out.println("***  D A T O S  **********************************************************");
		userRepo.save(new User("user1"));
		userRepo.save(new User("user2"));
		userRepo.save(new User("user3"));
	}

}

package com.example.apirest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApirestApplication implements  CommandLineRunner{
	/*Before runing :
	 * 	CREATE USER 'videocurse'@'localhost' IDENTIFIED BY 'admin';
	 * 	GRANT ALL PRIVILEGES ON *.* TO 'videocurse'@'localhost' WITH GRANT OPTION;
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		pruebas();
	}


	public void pruebas(){
		System.out.println("holamundo");
	}

}

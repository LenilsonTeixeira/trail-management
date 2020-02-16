package br.com.boraviajar.trailmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class TrailmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrailmanagementApplication.class, args);
	}

}

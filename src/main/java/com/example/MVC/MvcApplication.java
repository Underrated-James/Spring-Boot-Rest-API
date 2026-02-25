package com.example.MVC;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;


@SpringBootApplication
public class MvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcApplication.class, args);
        System.out.println("Hello World");
	}

    @Bean
    CommandLineRunner testMongo(MongoTemplate mongoTemplate){
        return args -> {
            System.out.println("MongoDB is connected: " + mongoTemplate.getDb().getName());
        };
    }

    //email :barcelonastella5@gmail.com
    //pass : 1234567890_Stella


}

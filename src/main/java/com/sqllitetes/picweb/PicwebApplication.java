package com.sqllitetes.picweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.sqllitetes.picweb")
public class PicwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicwebApplication.class, args);
	}

}

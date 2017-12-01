package com.dublabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication

@EnableJpaRepositories("com.dublabs.Repository")
public class VerifydemoApplication  extends SpringBootServletInitializer {




    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(VerifydemoApplication.class);
    }


	public static void main(String[] args) {
		SpringApplication.run(VerifydemoApplication.class, args);
	}



}

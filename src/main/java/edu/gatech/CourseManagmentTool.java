package edu.gatech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan("edu.gatech")
@EnableJpaRepositories("edu.gatech")
public class CourseManagmentTool extends SpringBootServletInitializer {




    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CourseManagmentTool.class);
    }


	public static void main(String[] args) {
		SpringApplication.run(CourseManagmentTool.class, args);
	}



}

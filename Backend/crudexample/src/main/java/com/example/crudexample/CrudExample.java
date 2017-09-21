package com.example.crudexample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableJpaRepositories(basePackages = "com.example.crudexample.domain.repo")
@PropertySource("classpath:/application.properties")
@EnableAutoConfiguration
public class CrudExample {
	public static void main(String[] args) {
		SpringApplication app =new SpringApplication(CrudExample.class);
		app.run(args);
	}
}

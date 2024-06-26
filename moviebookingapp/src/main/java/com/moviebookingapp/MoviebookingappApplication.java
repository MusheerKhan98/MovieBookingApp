package com.moviebookingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;


@SpringBootApplication
@EnableWebMvc
public class MoviebookingappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviebookingappApplication.class, args);
	}
}

package com.gameduca;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gameduca.entity.Asignatura;
import com.gameduca.repository.AsignaturaRepository;

@SpringBootApplication
public class GamEducaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamEducaApplication.class, args);
	}
	
//	@Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:4200")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .allowedHeaders("*");
//            }
//        };  
//    }
	
	@Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
                Usero user = new Usero(name, name.toLowerCase() + "@domain.com");
                userRepository.save(user);
            });
            userRepository.findAll().forEach(System.out::println);
        };
    }
	
	@Bean
    CommandLineRunner init2(AsignaturaRepository asignaturaRepository) {
        return args -> {
            Stream.of("Matematicas", "Lengua", "Fisica").forEach(name -> {
                Asignatura asignatura = new Asignatura(name, name.toLowerCase() + " La asignatura", "1");
                asignaturaRepository.save(asignatura);
            });
            asignaturaRepository.findAll().forEach(System.out::println);
        };
    }

}

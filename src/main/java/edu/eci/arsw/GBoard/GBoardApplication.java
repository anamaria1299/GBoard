package edu.eci.arsw.GBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Configuration
public class GBoardApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(GBoardApplication.class, args);
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/drawerJS/**").addResourceLocations("classpath:/drawerJS/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/drawerJS/images/");
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/drawerJS/assets/");
    }

}

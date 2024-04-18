package finonline.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//==import finonline.be.io.swagger.WebMvcConfigurerAdapter;
import finonline.be.io.swagger.configuration.LocalDateConverter;
import finonline.be.io.swagger.configuration.LocalDateTimeConverter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@ComponentScan(basePackages = {"finonline.be.mapper", "finonline.be", "finonline.be.web.request", "finonline.be.io.swagger.model", "finonline.be.io.swagger.configuration"})
public class FinonlineApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FinonlineApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
			}
			
			@Override
	        public void addFormatters(FormatterRegistry registry) {
	            registry.addConverter(new LocalDateConverter("yyyy-MM-dd"));
	            registry.addConverter(new LocalDateTimeConverter("yyyy-MM-dd'T'HH:mm:ss.SSS"));
	        }
		};
	}
}

package com.github.sparsick.workshop;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootWorkshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWorkshopApplication.class, args);
	}


	@Bean
	public OpenAPI api() {
		return new OpenAPI()
				.info(
						new Info()
								.title("Bookshelf API")
								.version("v0.0.1")
								.license(new License()
										.name("MIT License")
										.url("https://opensource.org/licenses/MIT")
								)
				);
	}

}

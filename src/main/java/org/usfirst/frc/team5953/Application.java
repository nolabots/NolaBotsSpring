package org.usfirst.frc.team5953;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages={"com.malachiular.solute.homepage"})
@EnableJpaRepositories
public class Application {
    
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Loads a (autogenerated) developer web interface mapped to DOMAIN/console.
	 * @return
	 */
    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }
}




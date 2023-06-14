package com.developerslike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@ComponentScan(basePackages = { "com.developerslike" },
excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, 
pattern = "com.developerslike.*"))

public class App extends SpringBootServletInitializer{

	public static void main(String[] args) {
SpringApplication.run(App.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder sab) {
		return sab.sources(App.class);
	}
}

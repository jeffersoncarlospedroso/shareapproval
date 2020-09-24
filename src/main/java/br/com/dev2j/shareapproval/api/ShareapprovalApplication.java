package br.com.dev2j.shareapproval.api;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ShareapprovalApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ShareapprovalApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ShareapprovalApplication.class);
	}
	
	
    @PostConstruct
    public void init(){
      // Setting Spring Boot SetTimeZone
      TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
      System.out.println("DATA Sao_Paulo: " + new Date().toString());

      ApiContextInitializer.init();

    }


    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

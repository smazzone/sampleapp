package com.datadog.ste.backend;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class BackendApplication {


  private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);


  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplateBuilder().build();
  }


  @Bean
  public CommandLineRunner run() {
    return args -> {
      log.info("test");
    };
  }

}
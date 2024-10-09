package com.murakamisoft.workflowtaskapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("com.murakamisoft.workflowtaskapi.mapper")
public class WorkflowTaskApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(WorkflowTaskApiApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}

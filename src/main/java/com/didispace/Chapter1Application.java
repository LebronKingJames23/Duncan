package com.didispace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
//@EnableDiscoveryClient
@SpringBootApplication
/*@EnableFeignClients
@EnableCircuitBreaker*/
@EnableAutoConfiguration
@EnableTransactionManagement
@ServletComponentScan
@ComponentScan("com.didispace.*")
@EnableScheduling
public class Chapter1Application {
	@Autowired
	RestTemplateBuilder rBuilder;
	
	@Bean
	public RestTemplate restTemplate() {
		return rBuilder.build();
	}
	public static void main(String[] args) {
		SpringApplication.run(Chapter1Application.class, args);
	}
}

package com.chiteshlab.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.chiteshlab.twitter")
@EnableConfigurationProperties
@EnableAutoConfiguration
public class TwitterClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterClientApplication.class, args);
	}
}

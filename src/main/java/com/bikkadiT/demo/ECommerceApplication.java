package com.bikkadiT.demo;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {


		SpringApplication.run(ECommerceApplication.class, args);
		System.out.println("Project running successfully");
	}


}

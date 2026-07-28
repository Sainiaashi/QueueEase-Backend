package com.aashi.QueueEase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class QueueEaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueueEaseApplication.class, args);
	}

}

package br.com.jobtechIO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JobtechIoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobtechIoApplication.class, args);
	}

}

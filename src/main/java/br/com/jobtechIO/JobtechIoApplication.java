package br.com.jobtechIO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.jobtechIO.configuration.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class JobtechIoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobtechIoApplication.class, args);
	}

}

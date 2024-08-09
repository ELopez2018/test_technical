package com.prueba;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PruebaTecnicaApplication {
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job importUserJob;
	public static void main(String[] args) {
		SpringApplication.run(PruebaTecnicaApplication.class, args);
	}

}

package com.gestao_academia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling; // 1. Adicione esse import

@SpringBootApplication
@EnableScheduling
public class GestaoAcademiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoAcademiaApplication.class, args);
	}

}
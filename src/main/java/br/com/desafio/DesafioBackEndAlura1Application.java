package br.com.desafio;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioBackEndAlura1Application {

	public static void main(String[] args){
		SpringApplication.run(DesafioBackEndAlura1Application.class, args);
		
		System.out.println(LocalDate.now().getMonthValue() - 1); 
		
		
	}

}

package edu.pe.cibertec.puntosmarket;

import edu.pe.cibertec.puntosmarket.config.DotenvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PuntosmarketApplication {

	public static void main(String[] args) {
		DotenvConfig.init();
		SpringApplication.run(PuntosmarketApplication.class, args);
	}

}

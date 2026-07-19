package edu.pe.cibertec.puntosmarket;

import edu.pe.cibertec.puntosmarket.config.DotenvConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PuntosmarketApplicationTests {
	static {
		DotenvConfig.init();
	}
	@Test
	void contextLoads() {
	}

}

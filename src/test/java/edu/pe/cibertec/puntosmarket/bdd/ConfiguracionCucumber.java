package edu.pe.cibertec.puntosmarket.bdd;

import edu.pe.cibertec.puntosmarket.config.DotenvConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@CucumberContextConfiguration
@SpringBootTest
@Transactional
public class ConfiguracionCucumber {
    static {
        DotenvConfig.init();
    }
}

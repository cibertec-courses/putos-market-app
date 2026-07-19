package edu.pe.cibertec.puntosmarket.bdd.steps;

import edu.pe.cibertec.puntosmarket.entity.Cliente;
import edu.pe.cibertec.puntosmarket.repository.ClienteRepository;
import edu.pe.cibertec.puntosmarket.service.ClienteService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CanjePuntosSteps {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;


    private String dniActual;
    private Double descuentoObtenido;
    private RuntimeException excepcionConfigurada;

    @Given("un cliente con DNI {string} registrado con {int} puntos y estado activo")
    public void un_cliente_con_dni_registrado_con_puntos_y_estado_activo(String dni, Integer puntos){
        prepararCliente(dni, puntos, true);
    }

    @Given("un cliente con DNI {string} registrado con {int} puntos y estado inactivo")
    public void un_cliente_con_dni_registrado_con_puntos_y_estado_inactivo(String dni, Integer puntos){
        prepararCliente(dni, puntos, false);
    }

    @When("el cliente canjea {int} puntos")
    public void el_cliente_canjea_puntos(Integer puntosACanjear){
        descuentoObtenido = clienteService.canjearPuntos(dniActual, puntosACanjear);
    }

    @When("el cliente intenta canjear {int} puntos")
    public void el_cliente_intenta_canjear_puntos(Integer puntosACanjear){
        try{
            clienteService.canjearPuntos(dniActual, puntosACanjear);
        } catch (RuntimeException e) {
            excepcionConfigurada = e;
        }
    }

    @Then("recibe un descuento de {double} soles")
    public void recibe_un_descuento_de_soles(Double descuentoEsperado){
        assertEquals(descuentoEsperado, descuentoObtenido);
    }

    @And("su saldo queda en {int} puntos")
    public void su_saldo_queda_en_puntos(Integer puntosEperados){
        Optional<Cliente> resultado = clienteRepository.findByDni(dniActual);
        Cliente cliente = resultado.get();
        assertEquals(puntosEperados, cliente.getPuntos());
    }

    @And("el canje es rechazado con el mensaje {string}")
    public void el_canje_es_rechazado_con_el_mensaje(String mensajeEsperado){
        assertNotNull(excepcionConfigurada);
        assertEquals(mensajeEsperado, excepcionConfigurada.getMessage());
    }

    private void prepararCliente(String dni, Integer puntos, Boolean activo){
        dniActual = dni;
        descuentoObtenido = null;
        excepcionConfigurada = null;

        Optional<Cliente> resultado = clienteRepository.findByDni(dni);
        Cliente cliente;

        if(resultado.isPresent()){
            cliente = resultado.get();
        }else{
            cliente = Cliente.builder()
                    .nombre("Cliente BDD "+ dni)
                    .dni(dni)
                    .build();

            cliente.setPuntos(puntos);
            cliente.setActivo(activo);
            clienteRepository.save(cliente);
        }





    }



}

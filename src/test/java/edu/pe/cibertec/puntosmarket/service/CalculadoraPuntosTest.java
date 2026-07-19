package edu.pe.cibertec.puntosmarket.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraPuntosTest {
    private CalculadoraPuntos calculadora;
    @BeforeEach
    void configurar(){
        calculadora = new CalculadoraPuntos();
    }

    /// Una compra de 150.00 debe generar 15 puntos
    @Test
    @DisplayName("Una compra de 150.00 debe generar 15 puntos")
    void calcularPuntos_compraDe150_genera15Puntos(){
        Double montoCompra = 150.0;
        Integer puntosGanados = calculadora.calcularPuntos(montoCompra);
        assertEquals(15, puntosGanados);

    }

    /// Una compra de 9.99 no debe generar puntos
    @Test
    @DisplayName("Una compra de 9.99 no debe generar puntos")
    void calcularPuntos_compraMenorA10_generaCeroPuntos(){
        Double montoCompra = 9.99;
        Integer puntosGanados = calculadora.calcularPuntos(montoCompra);
        assertEquals(0, puntosGanados);
    }

    /// Un monto negativo debe lanzar una exception
    @Test
    @DisplayName("Un monto negativo debe lanzar una exception")
    void calcularPuntos_montoNegativo_lanzaExcepcion(){
        Double montoCompra = -19.99;

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> calculadora.calcularPuntos(montoCompra));

        assertEquals("El monto de compra debe ser mayor a cero", exception.getLocalizedMessage());

    }
}
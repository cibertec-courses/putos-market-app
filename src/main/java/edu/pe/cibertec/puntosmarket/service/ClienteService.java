package edu.pe.cibertec.puntosmarket.service;

import edu.pe.cibertec.puntosmarket.entity.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente registrarCliente(Cliente cliente);

    Cliente acumularPuntos(String dni, Double montoCompra);

    Double canjearPuntos(String dni, Integer puntosACanjear);

    List<Cliente> listarClientesActivos();

    List<Cliente> listarClientesQuePuedenCanjear();
}

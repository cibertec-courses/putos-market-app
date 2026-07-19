package edu.pe.cibertec.puntosmarket.service.Impl;

import edu.pe.cibertec.puntosmarket.entity.Cliente;
import edu.pe.cibertec.puntosmarket.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;

import edu.pe.cibertec.puntosmarket.service.ClienteService;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente registrarCliente(Cliente cliente) {
        Optional<Cliente> existente = clienteRepository.findByDni(cliente.getDni());
        if (existente.isPresent()) {
            throw new RuntimeException("Ya existe un cliente con el DNI " + cliente.getDni());
        }
        cliente.setPuntos(0);
        cliente.setActivo(true);
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente acumularPuntos(String dni, Double montoCompra) {
        if (montoCompra == null || montoCompra <= 0) {
            throw new RuntimeException("El monto de compra debe ser mayor a cero");
        }
        Optional<Cliente> resultado = clienteRepository.findByDni(dni);
        if (!resultado.isPresent()) {
            throw new RuntimeException("No existe un cliente con el DNI " + dni);
        }
        Cliente cliente = resultado.get();
        if (!cliente.getActivo()) {
            throw new RuntimeException("El cliente esta inactivo");
        }
        int puntosGanados = (int) (montoCompra / 10);
        cliente.setPuntos(cliente.getPuntos() + puntosGanados);
        return clienteRepository.save(cliente);
    }

    @Override
    public Double canjearPuntos(String dni, Integer puntosACanjear) {
        Optional<Cliente> resultado = clienteRepository.findByDni(dni);
        if (!resultado.isPresent()) {
            throw new RuntimeException("No existe un cliente con el DNI " + dni);
        }
        Cliente cliente = resultado.get();
        if (!cliente.getActivo()) {
            throw new RuntimeException("El cliente esta inactivo");
        }
        if (cliente.getPuntos() < 50) {
            throw new RuntimeException("Se necesita un minimo de 50 puntos para canjear");
        }
        if (puntosACanjear > cliente.getPuntos()) {
            throw new RuntimeException("No puede canjear mas puntos de los que tiene");
        }
        cliente.setPuntos(cliente.getPuntos() - puntosACanjear);
        clienteRepository.save(cliente);
        return puntosACanjear * 0.50;
    }

    @Override
    public List<Cliente> listarClientesActivos() {
        return clienteRepository.findByActivoTrue();
    }

    @Override
    public List<Cliente> listarClientesQuePuedenCanjear() {
        return clienteRepository.findByPuntosGreaterThanEqual(50);
    }

}

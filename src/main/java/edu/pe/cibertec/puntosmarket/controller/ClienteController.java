package edu.pe.cibertec.puntosmarket.controller;

import edu.pe.cibertec.puntosmarket.entity.Cliente;
import edu.pe.cibertec.puntosmarket.service.ClienteService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {
        Cliente clienteRegistrado = clienteService.registrarCliente(cliente);
        return new ResponseEntity<>(clienteRegistrado, HttpStatus.CREATED);
    }

    @PutMapping("/{dni}/acumular")
    public ResponseEntity<Cliente> acumularPuntos(
            @PathVariable String dni,
            @RequestParam Double montoCompra) {
        Cliente clienteActualizado = clienteService.acumularPuntos(dni, montoCompra);
        return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
    }

    @PutMapping("/{dni}/canjear")
    public ResponseEntity<Double> canjearPuntos(
            @PathVariable String dni,
            @RequestParam Integer puntosACanjear) {
        Double descuento = clienteService.canjearPuntos(dni, puntosACanjear);
        return new ResponseEntity<>(descuento, HttpStatus.OK);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Cliente>> listarClientesActivos() {
        return new ResponseEntity<>(clienteService.listarClientesActivos(), HttpStatus.OK);
    }

    @GetMapping("/pueden-canjear")
    public ResponseEntity<List<Cliente>> listarClientesQuePuedenCanjear() {
        return new ResponseEntity<>(clienteService.listarClientesQuePuedenCanjear(), HttpStatus.OK);
    }
}
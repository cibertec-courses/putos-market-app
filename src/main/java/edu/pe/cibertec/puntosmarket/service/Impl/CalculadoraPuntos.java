package edu.pe.cibertec.puntosmarket.service.Impl;

public class CalculadoraPuntos {

    public Integer calcularPuntos(Double montoCompra){
        if(montoCompra == null || montoCompra <=0){
            throw  new RuntimeException("El monto de compra debe ser mayor a cero");
        }
        return  (int) (montoCompra/10);
    }
}

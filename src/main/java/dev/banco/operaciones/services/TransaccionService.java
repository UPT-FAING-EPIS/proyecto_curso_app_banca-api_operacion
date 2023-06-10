package dev.banco.operaciones.services;

import dev.banco.operaciones.controllers.dtos.TransaccionDTO;
import dev.banco.operaciones.models.CuentaBancaria;
import dev.banco.operaciones.models.Transaccion;

import java.util.List;
import java.util.Optional;

public interface TransaccionService {
    Transaccion realizarTransaccion(TransaccionDTO transaccion);
    List<Transaccion> findAll();

    List<Transaccion> findByNumeroCuenta(String numeroCuenta);
}

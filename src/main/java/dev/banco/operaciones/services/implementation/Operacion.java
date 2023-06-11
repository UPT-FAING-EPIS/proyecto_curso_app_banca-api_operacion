package dev.banco.operaciones.services.implementation;

import dev.banco.operaciones.models.CuentaBancaria;

import java.math.BigDecimal;

public interface Operacion {

    void realizarOperacion(CuentaBancaria cuentaBancaria, BigDecimal monto);
}

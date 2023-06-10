package dev.banco.operaciones.services.implementation;

import dev.banco.operaciones.models.CuentaBancaria;

import java.math.BigDecimal;

public class DepositoOperacion implements OperacionTransaccion {

    @Override
    public void realizarOperacion(CuentaBancaria cuentaBancaria, BigDecimal monto) {
        cuentaBancaria.abonar(monto);
    }
}

package dev.banco.operaciones.services;

import dev.banco.operaciones.models.CuentaBancaria;

public interface ConsultaSaldoService {
    CuentaBancaria realizarConsultaSaldo(String numeroCuenta);
}

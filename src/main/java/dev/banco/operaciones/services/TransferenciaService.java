package dev.banco.operaciones.services;

import dev.banco.operaciones.models.Transferencia;

import java.math.BigDecimal;

public interface TransferenciaService {

    Transferencia realizarTranferencia(Transferencia transferencia);
}

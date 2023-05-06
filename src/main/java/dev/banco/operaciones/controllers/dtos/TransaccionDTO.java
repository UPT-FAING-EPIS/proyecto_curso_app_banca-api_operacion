package dev.banco.operaciones.controllers.dtos;

import dev.banco.operaciones.models.TipoTransaccion;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransaccionDTO {
    private BigDecimal monto;
    private String tipo;
    private String numeroCuenta;

    public void validarTipoOperacion() {
        try {
            TipoTransaccion.valueOf(this.tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de operación inválido.. Solo se permite DEPOSITO o RETIRO");
        }
    }
}

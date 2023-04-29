package dev.banco.operaciones.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class CuentaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCuenta;
    private BigDecimal saldo;

    public void debitar(BigDecimal monto) {
        this.saldo = saldo.subtract(monto);
    }

    public void abonar(BigDecimal monto) {
        this.saldo = saldo.add(monto);
    }
}

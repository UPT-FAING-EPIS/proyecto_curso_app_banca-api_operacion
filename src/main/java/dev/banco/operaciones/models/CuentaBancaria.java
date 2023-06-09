package dev.banco.operaciones.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class CuentaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCuenta;
    private BigDecimal saldo;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaccion> transacciones;

    public void debitar(BigDecimal monto) {
        this.saldo = saldo.subtract(monto);
    }

    public void abonar(BigDecimal monto) {
        this.saldo = saldo.add(monto);
    }
}

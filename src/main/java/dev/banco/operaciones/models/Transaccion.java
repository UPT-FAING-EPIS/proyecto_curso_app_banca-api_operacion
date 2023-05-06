package dev.banco.operaciones.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="transacciones")
@Data
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cuenta")
    private CuentaBancaria cuenta;

    private LocalDateTime fechaHoraTransaccion;

    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    private TipoTransaccion tipo;
}

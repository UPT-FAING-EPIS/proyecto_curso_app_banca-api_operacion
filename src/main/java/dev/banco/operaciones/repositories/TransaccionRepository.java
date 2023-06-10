package dev.banco.operaciones.repositories;

import dev.banco.operaciones.models.CuentaBancaria;
import dev.banco.operaciones.models.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    Optional<List<Transaccion>> findByCuentaNumeroCuenta(String numeroCuenta);
}

package dev.banco.operaciones.repositories;

import dev.banco.operaciones.models.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositosRepository extends JpaRepository<Deposito, Long> {
}

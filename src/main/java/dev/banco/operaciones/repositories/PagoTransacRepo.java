package dev.banco.operaciones.repositories;

import dev.banco.operaciones.models.PagoTransac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoTransacRepo extends JpaRepository<PagoTransac, Integer> {
}

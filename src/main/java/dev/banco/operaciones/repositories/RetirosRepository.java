package dev.banco.operaciones.repositories;

import dev.banco.operaciones.models.Retiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetirosRepository extends JpaRepository<Retiro, Long> {

}

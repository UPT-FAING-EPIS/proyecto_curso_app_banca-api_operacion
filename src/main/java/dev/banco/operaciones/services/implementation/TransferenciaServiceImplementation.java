package dev.banco.operaciones.services.implementation;

import dev.banco.operaciones.models.CuentaBancaria;
import dev.banco.operaciones.models.Transferencia;
import dev.banco.operaciones.repositories.CuentaBancariaRepository;
import dev.banco.operaciones.repositories.TransferenciaRepository;
import dev.banco.operaciones.services.TransferenciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransferenciaServiceImplementation implements TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Override
    public Transferencia realizarTranferencia(Transferencia transferencia) {
        log.info("Realizando transferencia: {}", transferencia);
        CuentaBancaria origen = this.cuentaBancariaRepository
                .findByNumeroCuenta(transferencia.getCuentaOrigen())
                .orElse(null);
        CuentaBancaria destino = this.cuentaBancariaRepository
                .findByNumeroCuenta(transferencia.getCuentaDestino())
                .orElse(null);

        if (origen == null || destino == null) {
            throw new IllegalArgumentException("Cuentas de origen o destino inválidas");
        }

        if (origen.getSaldo().compareTo(transferencia.getMonto()) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente en la cuenta de origen");
        }

        // Realizar la transferencia
        transferencia = transferenciaRepository.save(transferencia);
        log.info("Tranferencia realizada: {}", transferencia);

        // Actualizar los saldos de las cuentas
        origen.debitar(transferencia.getMonto());
        destino.abonar(transferencia.getMonto());

        // Actualizar las cuentas en el repositorio
        this.cuentaBancariaRepository.save(origen);
        this.cuentaBancariaRepository.save(destino);

        return transferencia;

    }

}

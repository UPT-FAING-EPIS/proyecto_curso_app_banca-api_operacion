package dev.banco.operaciones.services.implementation;

import dev.banco.operaciones.models.CuentaBancaria;
import dev.banco.operaciones.repositories.CuentaBancariaRepository;
import dev.banco.operaciones.services.ConsultaSaldoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultaSaldoServiceImplementation implements ConsultaSaldoService {
    @Autowired
    CuentaBancariaRepository cuentaBancariaRepository;

    @Override
    public CuentaBancaria realizarConsultaSaldo(String numeroCuenta) {
        Logger logger = LoggerFactory.getLogger(ConsultaSaldoServiceImplementation.class);
        logger.info("NumCuen:" + numeroCuenta);
        CuentaBancaria cuentaBancaria = obtenerCuentaBancaria(numeroCuenta);
        if (cuentaBancaria == null) {
            throw new IllegalArgumentException("Número de cuenta no registrada.. ");
        }

        return cuentaBancaria;
    }

    private CuentaBancaria obtenerCuentaBancaria(String numeroCuenta) {
        Optional<CuentaBancaria> cuentaBancariaOptional = this.cuentaBancariaRepository.findByNumeroCuenta(numeroCuenta);
        return cuentaBancariaOptional.orElse(null);
    }
}

package dev.banco.operaciones.services.implementation;

import dev.banco.operaciones.models.CuentaBancaria;
import dev.banco.operaciones.models.Deposito;
import dev.banco.operaciones.repositories.CuentaBancariaRepository;
import dev.banco.operaciones.repositories.DepositosRepository;
import dev.banco.operaciones.services.DepositosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositosServiceImplementation implements DepositosService {

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    private DepositosRepository depositosRepository;

    @Override
    public Deposito realizarDeposito(Deposito deposito) {

        CuentaBancaria cuentaBancaria = obtenerCuentaBancaria(deposito.getNumeroCuenta());

        if (cuentaBancaria == null) {
            throw new IllegalArgumentException("Número de cuenta invalida.. ");
        }

        Deposito depositoGuardado = depositosRepository.save(deposito);

        cuentaBancaria.abonar(deposito.getMonto());

        cuentaBancariaRepository.save(cuentaBancaria);

        return depositoGuardado;
    }

    private CuentaBancaria obtenerCuentaBancaria(String numeroCuenta) {
        Optional<CuentaBancaria> cuentaBancariaOptional = this.cuentaBancariaRepository.findByNumeroCuenta(numeroCuenta);
        return cuentaBancariaOptional.orElse(null);
    }
}

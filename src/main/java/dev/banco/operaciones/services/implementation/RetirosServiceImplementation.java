package dev.banco.operaciones.services.implementation;

import dev.banco.operaciones.models.CuentaBancaria;
import dev.banco.operaciones.models.Retiro;
import dev.banco.operaciones.repositories.CuentaBancariaRepository;
import dev.banco.operaciones.repositories.RetirosRepository;
import dev.banco.operaciones.services.RetirosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RetirosServiceImplementation implements RetirosService {

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    private RetirosRepository retirosRepository;

    @Override
    public Retiro realizarRetiro(Retiro retiro) {
        CuentaBancaria cuentaBancaria = obtenerCuentaBancaria(retiro.getNumeroCuenta());

        if (cuentaBancaria == null) {
            throw new IllegalArgumentException("Número de cuenta invalida.. ");
        }

        if (cuentaBancaria.getSaldo().compareTo(retiro.getMonto()) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente en la cuenta..");
        }

        Retiro retiroGuardado = retirosRepository.save(retiro);

        cuentaBancaria.debitar(retiro.getMonto());

        cuentaBancariaRepository.save(cuentaBancaria);

        return retiroGuardado;
    }

    private CuentaBancaria obtenerCuentaBancaria(String numeroCuenta) {
        Optional<CuentaBancaria> cuentaBancariaOptional = this.cuentaBancariaRepository.findByNumeroCuenta(numeroCuenta);
        return cuentaBancariaOptional.orElse(null);
    }
}

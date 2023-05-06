package dev.banco.operaciones.services.implementation;

import dev.banco.operaciones.controllers.dtos.TransaccionDTO;
import dev.banco.operaciones.models.CuentaBancaria;
import dev.banco.operaciones.models.TipoTransaccion;
import dev.banco.operaciones.models.Transaccion;
import dev.banco.operaciones.repositories.CuentaBancariaRepository;
import dev.banco.operaciones.repositories.TransaccionRepository;
import dev.banco.operaciones.services.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionServiceImplementation implements TransaccionService {
    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Override
    public Transaccion realizarTransaccion(TransaccionDTO transaccionDTO) {
        Transaccion transaccion = this.crearTransaccion(transaccionDTO);

        return transaccionRepository.save(transaccion);
    }

    @Override
    public List<Transaccion> findAll() {
        return this.transaccionRepository.findAll();
    }

    private Transaccion crearTransaccion(TransaccionDTO transaccionDTO) {

        transaccionDTO.validarTipoOperacion();

        CuentaBancaria cuentaBancaria = obtenerCuentaBancaria(transaccionDTO.getNumeroCuenta());

        if (cuentaBancaria == null) {
            throw new IllegalArgumentException("Número de cuenta invalida.. ");
        }

        if (cuentaBancaria.getSaldo().compareTo(transaccionDTO.getMonto()) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente en la cuenta..");
        }

        // Creamos una nueva instancia de Transaccion y asignamos los valores correspondientes
        Transaccion transaccion = new Transaccion();
        transaccion.setMonto(transaccionDTO.getMonto());
        transaccion.setTipo(TipoTransaccion.valueOf(transaccionDTO.getTipo().toUpperCase()));
        transaccion.setCuenta(cuentaBancaria);
        transaccion.setFechaHoraTransaccion(LocalDateTime.now());

        if(transaccion.getTipo() == TipoTransaccion.RETIRO) {
            cuentaBancaria.debitar(transaccionDTO.getMonto());
        } else if(transaccion.getTipo() == TipoTransaccion.DEPOSITO){
            cuentaBancaria.abonar(transaccionDTO.getMonto());
        }

        cuentaBancariaRepository.save(cuentaBancaria);

        return transaccion;
    }

    private CuentaBancaria obtenerCuentaBancaria(String numeroCuenta) {
        Optional<CuentaBancaria> cuentaBancariaOptional = this.cuentaBancariaRepository.findByNumeroCuenta(numeroCuenta);
        return cuentaBancariaOptional.orElse(null);
    }
}

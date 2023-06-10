package dev.banco.operaciones.services.implementation;

import dev.banco.operaciones.controllers.dtos.TransaccionDTO;
import dev.banco.operaciones.models.*;
import dev.banco.operaciones.repositories.CuentaBancariaRepository;
import dev.banco.operaciones.repositories.TransaccionRepository;
import dev.banco.operaciones.services.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransaccionServiceImplementation implements TransaccionService {
    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    private final Map<TipoTransaccion, OperacionTransaccion> operaciones;

    public TransaccionServiceImplementation() {
        this.operaciones = new HashMap<>();
        operaciones.put(TipoTransaccion.RETIRO, new RetiroOperacion());
        operaciones.put(TipoTransaccion.DEPOSITO, new DepositoOperacion());
    }

    @Override
    public Transaccion realizarTransaccion(TransaccionDTO transaccionDTO) {
        Transaccion transaccion = this.crearTransaccion(transaccionDTO);

        return transaccionRepository.save(transaccion);
    }

    @Override
    public List<Transaccion> findAll() {
        return this.transaccionRepository.findAll();
    }

    @Override
    public List<Transaccion> findByNumeroCuenta(String numeroCuenta) {
        return this.transaccionRepository.findByCuentaNumeroCuenta(numeroCuenta).orElse(null);
    }

    private Transaccion crearTransaccion(TransaccionDTO transaccionDTO) {

        transaccionDTO.validarTipoOperacion();

        CuentaBancaria cuentaBancaria = this.cuentaBancariaRepository.findByNumeroCuenta(transaccionDTO.getNumeroCuenta()).orElse(null);

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

        OperacionTransaccion operacion = this.operaciones.get(transaccion.getTipo());
        operacion.realizarOperacion(cuentaBancaria, transaccionDTO.getMonto());

        cuentaBancariaRepository.save(cuentaBancaria);

        return transaccion;
    }
}

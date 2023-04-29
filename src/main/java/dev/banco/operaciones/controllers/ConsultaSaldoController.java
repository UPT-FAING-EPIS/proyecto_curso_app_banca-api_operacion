package dev.banco.operaciones.controllers;

import dev.banco.operaciones.models.CuentaBancaria;
import dev.banco.operaciones.models.Response;
import dev.banco.operaciones.services.ConsultaSaldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/consultasaldo")
public class ConsultaSaldoController {

    @Autowired
    private ConsultaSaldoService consultaSaldoService;

    @PostMapping("/")
    public ResponseEntity<Response> realizarDeposito(@RequestBody String numeroCuenta) {
        CuentaBancaria cuentaBancaria = consultaSaldoService.realizarConsultaSaldo(numeroCuenta);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Consulta realizada con éxito")
                        .status(OK)
                        .data(Map.of("cuentaBancaria", cuentaBancaria))
                        .build()
        );
    }
}

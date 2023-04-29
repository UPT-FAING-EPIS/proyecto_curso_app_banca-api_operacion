package dev.banco.operaciones.controllers;

import dev.banco.operaciones.models.Deposito;
import dev.banco.operaciones.models.Response;
import dev.banco.operaciones.models.Transferencia;
import dev.banco.operaciones.services.DepositosService;
import dev.banco.operaciones.services.TransferenciaService;
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
@RequestMapping("/depositos")
public class GestionarDepositosController {

    @Autowired
    private DepositosService depositosService;

    @PostMapping("/")
    public ResponseEntity<Response> realizarDeposito(@RequestBody Deposito deposito) {
        Deposito depositoRealizado = depositosService.realizarDeposito(deposito);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Deposito realizado con éxito")
                        .status(OK)
                        .data(Map.of("deposito", depositoRealizado))
                        .build()
        );
    }

}

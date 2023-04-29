package dev.banco.operaciones.controllers;

import dev.banco.operaciones.models.Response;
import dev.banco.operaciones.models.Retiro;
import dev.banco.operaciones.services.RetirosService;
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
@RequestMapping("/retiros")
public class GestionarRetirosController {

    @Autowired
    private RetirosService retirosService;

    @PostMapping("/")
    public ResponseEntity<Response> realizarDeposito(@RequestBody Retiro retiro) {
        Retiro retiroRealizado = retirosService.realizarRetiro(retiro);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Retiro realizado con éxito")
                        .status(OK)
                        .data(Map.of("retiro", retiroRealizado))
                        .build()
        );
    }
}

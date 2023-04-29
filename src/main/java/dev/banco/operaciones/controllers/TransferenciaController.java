package dev.banco.operaciones.controllers;

import dev.banco.operaciones.models.Response;
import dev.banco.operaciones.models.Transferencia;
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
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping("/")
    public ResponseEntity<Response> realizarTransferencia(@RequestBody Transferencia transferencia) {
        Transferencia transferenciaRealizada = transferenciaService.realizarTranferencia(transferencia);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Transferencia realizada con éxito")
                        .status(OK)
                        .data(Map.of("transferencia", transferenciaRealizada))
                        .build()
        );
    }
}

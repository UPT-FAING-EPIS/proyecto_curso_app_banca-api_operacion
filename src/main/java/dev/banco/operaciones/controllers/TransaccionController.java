package dev.banco.operaciones.controllers;

import dev.banco.operaciones.controllers.dtos.TransaccionDTO;
import dev.banco.operaciones.models.Response;
import dev.banco.operaciones.models.Transaccion;
import dev.banco.operaciones.services.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping()
    public ResponseEntity<Response> findAll(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Transacciones realizadas")
                        .status(OK)
                        .data(Map.of("transacciones", this.transaccionService.findAll()))
                        .build()
        );
    }

    @PostMapping()
    public ResponseEntity<Response> realizarTransaccion(@RequestBody TransaccionDTO transaccionDTO) {
        Transaccion transaccionRealizada = this.transaccionService.realizarTransaccion(transaccionDTO);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message(transaccionRealizada.getTipo().toString().toLowerCase() + " realizado con éxito")
                        .status(OK)
                        .data(Map.of("transaccion", transaccionRealizada))
                        .build()
        );
    }
}

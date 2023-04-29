package dev.banco.operaciones.controllers;

import dev.banco.operaciones.models.PagoTransac;
import dev.banco.operaciones.services.PagoTransacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagotarjertas")
public class PagoTransacControl {
    @Autowired
    private PagoTransacService pagotransacservice;

    @GetMapping(value = "/")
    public String helloWorld(){
        return "Hola mundo";
    }

    @GetMapping(value = "/lpagotransac")
    public List<PagoTransac> listar(){
        return pagotransacservice.listar();
    }

    @PostMapping(value = "/ipagotransac")
    public PagoTransac insertar(@RequestBody PagoTransac pago){
        return pagotransacservice.insertar(pago);
    }
}

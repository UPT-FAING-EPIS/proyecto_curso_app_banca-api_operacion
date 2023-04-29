package dev.banco.operaciones.services;

import dev.banco.operaciones.models.PagoTransac;
import dev.banco.operaciones.repositories.PagoTransacRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoTransacService {
    @Autowired
    private PagoTransacRepo pagotransacrepo;

    public PagoTransac insertar(PagoTransac pago){
        return pagotransacrepo.save(pago);
    }

    public List<PagoTransac> listar(){
        return pagotransacrepo.findAll();
    }
}

package dev.banco.operaciones.services.implementation;

import dev.banco.operaciones.models.TipoTransaccion;

import java.util.HashMap;
import java.util.Map;

public class OperacionFactory {

    private static Map<TipoTransaccion, Operacion> operaciones;

    static {
        OperacionFactory.operaciones = new HashMap<>();
        OperacionFactory.operaciones.put(TipoTransaccion.RETIRO, new RetiroOperacion());
        OperacionFactory.operaciones.put(TipoTransaccion.DEPOSITO, new DepositoOperacion());
    }

    public static Operacion getOperacion(TipoTransaccion tipoTransaccion)
    {
        return OperacionFactory.operaciones.get(tipoTransaccion);
    }
}

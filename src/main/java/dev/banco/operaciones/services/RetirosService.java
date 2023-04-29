package dev.banco.operaciones.services;

import dev.banco.operaciones.models.Deposito;
import dev.banco.operaciones.models.Retiro;

public interface RetirosService {

    Retiro realizarRetiro(Retiro retiro);
}

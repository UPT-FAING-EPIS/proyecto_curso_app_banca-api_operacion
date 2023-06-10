import dev.banco.operaciones.controllers.TransferenciaController;
import dev.banco.operaciones.models.Response;
import dev.banco.operaciones.models.Transferencia;
import dev.banco.operaciones.services.TransferenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransferenciaControllerTest {
    @Mock
    private TransferenciaService transferenciaService;

    @InjectMocks
    private TransferenciaController transferenciaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRealizarTransferencia() {
        // Crear una transferencia de prueba
        Transferencia transferencia = Transferencia.builder()
                .id(1L)
                .cuentaOrigen("123456")
                .cuentaDestino("789012")
                .monto(BigDecimal.valueOf(100))
                .fechaHoraTransaccion(LocalDateTime.now())
                .build();

        // Crear una transferencia de prueba realizada
        Transferencia transferenciaRealizada = Transferencia.builder()
                .id(1L)
                .cuentaOrigen("123456")
                .cuentaDestino("789012")
                .monto(BigDecimal.valueOf(100))
                .fechaHoraTransaccion(LocalDateTime.now())
                .build();

        // Configurar el comportamiento del servicio
        when(transferenciaService.realizarTranferencia(transferencia)).thenReturn(transferenciaRealizada);

        // Realizar la llamada a la API
        ResponseEntity<Response> response = transferenciaController.realizarTransferencia(transferencia);

        // Verificar que el servicio fue llamado una vez
        verify(transferenciaService, times(1)).realizarTranferencia(transferencia);

        // Verificar la respuesta de la API
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Response responseBody = response.getBody();
        assertEquals("Transferencia realizada con éxito", responseBody.getMessage());
        Map<String, Object> data = (Map<String, Object>) responseBody.getData();
        assertEquals(transferenciaRealizada, data.get("transferencia"));
    }
}

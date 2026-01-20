package com.oficina.api.web;

import com.oficina.api.application.ClienteService;
import com.oficina.api.domain.entity.Cliente;
import com.oficina.api.web.controller.ClienteController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class ClienteControllerTest {
    private final ClienteService service = Mockito.mock(ClienteService.class);

    @Test
    void testValidarDocumentoCpfValido() {
        ClienteController controller = new ClienteController(service);
        assertTrue(controller.validarDocumento("12345678901"));
    }

    @Test
    void testValidarDocumentoCnpjValido() {
        ClienteController controller = new ClienteController(service);
        assertTrue(controller.validarDocumento("12345678000199"));
    }

    @Test
    void testValidarDocumentoInvalido() {
        ClienteController controller = new ClienteController(service);
        assertFalse(controller.validarDocumento("123"));
    }

    @Test
    void testGetCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Teste");
        cliente.setCpfOuCnpj("12345678901");
        Mockito.when(service.findCliente(1L)).thenReturn(Optional.of(cliente));
        ClienteController controller = new ClienteController(service);
        assertEquals("Teste", controller.getCliente(1L).getNome());
    }
}

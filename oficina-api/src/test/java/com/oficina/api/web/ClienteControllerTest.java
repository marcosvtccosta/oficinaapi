package com.oficina.api.web;

import com.oficina.api.domain.Cliente;
import com.oficina.api.domain.ClienteRepository;
import com.oficina.api.web.controller.ClienteController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class ClienteControllerTest {
    @Test
    void testValidarDocumentoCpfValido() {
        ClienteController controller = new ClienteController();
        assertTrue(controller.validarDocumento("12345678901"));
    }

    @Test
    void testValidarDocumentoCnpjValido() {
        ClienteController controller = new ClienteController();
        assertTrue(controller.validarDocumento("12345678000199"));
    }

    @Test
    void testValidarDocumentoInvalido() {
        ClienteController controller = new ClienteController();
        assertFalse(controller.validarDocumento("123"));
    }

    @Test
    void testGetCliente() {
        ClienteRepository repo = Mockito.mock(ClienteRepository.class);
        Cliente cliente = Cliente.builder().id(1L).nome("Teste").cpfOuCnpj("12345678901").build();
        Mockito.when(repo.findById(1L)).thenReturn(cliente);
        ClienteController controller = new ClienteController();
        controller.clienteRepository = repo;
        assertEquals("Teste", controller.getCliente(1L).getNome());
    }
}


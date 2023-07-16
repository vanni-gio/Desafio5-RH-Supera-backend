package br.com.banco.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.banco.controllers.BancoController;
import br.com.banco.entities.ContaEntity;
import br.com.banco.services.implementation.ContaServiceImpl;
import br.com.banco.services.implementation.TransferenciaServiceImpl;

public class BancoControllerTeste {
    @Mock

    private TransferenciaServiceImpl transferenciaService = new TransferenciaServiceImpl();

    @Mock
    private ContaServiceImpl contaService = new ContaServiceImpl();

    @InjectMocks
    private BancoController bancoController;

    @Test
    public void testGetContaById_ExistingConta() {
        List<ContaEntity> mockContasEntity = Arrays.asList(
            new ContaEntity(1L, 500.11, 123452L, "13232466723", "Fulano"),
            new ContaEntity(2L, 1500.22, 743214L, "12345678910", "Sicrano")
    );
        when(contaService.getAll()).thenReturn(mockContasEntity);

        
        ResponseEntity<Object> response = bancoController.getAllContas(null, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockContasEntity, response.getBody());
    }

}

package br.com.banco.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.banco.entities.ContaEntity;
import br.com.banco.exceptions.ContaNotFoundException;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.services.implementation.ContaServiceImpl;


@ExtendWith(MockitoExtension.class)
public class ContaServiceImplTest {

    @Mock
    private ContaRepository contaRepository; 

    @InjectMocks
    private ContaServiceImpl contaService;

    @Test
    public void testGetTransferenciaById_ExistingTransferencia() throws ParseException {
        Long contaId = 1L; 

        ContaEntity mockConta = new ContaEntity(contaId, 500.11, 123452L, "13232466723", "Fulano");
        when(contaRepository.findById(contaId)).thenReturn(Optional.of(mockConta));

        // Quando buscamos o usuário pelo ID
        ContaEntity transferenciaEntity = null;
        try {
            transferenciaEntity = contaService.getById(contaId).get();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Então o resultado deve ser o usuário correto no formato de DTO
        assertNotEquals(transferenciaEntity, null);
        assertEquals(mockConta.getId(), transferenciaEntity.getId());
        assertEquals(mockConta.getCpf(), transferenciaEntity.getCpf());
        assertEquals(mockConta.getSaldoTotal(), transferenciaEntity.getSaldoTotal());
        assertEquals(mockConta.getNroConta(), transferenciaEntity.getNroConta());
        assertEquals(mockConta.getNomeResponsavel(), transferenciaEntity.getNomeResponsavel());
    }

    @Test
    public void testGetUserById_NonExistentUser() {
        Long nonExistentContaId = 100L;
        when(contaRepository.findById(nonExistentContaId)).thenReturn(Optional.empty());

        assertThrows(ContaNotFoundException.class, () -> contaService.getById(nonExistentContaId));
    }
}

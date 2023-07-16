package br.com.banco.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.banco.entities.TransferenciaEntity;
import br.com.banco.exceptions.TransferenciaNotFoundException;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.services.implementation.TransferenciaServiceImpl;
import br.com.banco.types.TipoTransacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransferenciaServiceImplTest {

    @Mock
    private TransferenciaRepository transferenciaRepository; 

    @InjectMocks
    private TransferenciaServiceImpl transferenciaService;

    @Test
    public void testGetTransferenciaById_ExistingTransferencia() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long userId = 1L;
        TransferenciaEntity mockTransferencia = new TransferenciaEntity(userId,
            dateFormat.parse("2019-01-01 12:00:00+03"),
            30895.46,
            TipoTransacao.DEPOSITO,
            ""
        );
        when(transferenciaRepository.findById(userId)).thenReturn(Optional.of(mockTransferencia));

        // Quando buscamos o usuário pelo ID
        TransferenciaEntity transferenciaEntity = null;
        try {
            transferenciaEntity = transferenciaService.getTransferenciaById(userId).get();
        } catch (br.com.banco.services.implementation.TransferenciaNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Então o resultado deve ser o usuário correto no formato de DTO
        assertNotEquals(transferenciaEntity, null);
        assertEquals(mockTransferencia.getDataTransferencia(), transferenciaEntity.getDataTransferencia());
        assertEquals(mockTransferencia.getValor(), transferenciaEntity.getValor());
        assertEquals(mockTransferencia.getTipo(), transferenciaEntity.getTipo());
        assertEquals(mockTransferencia.getNomeOperadorTransferencia(), transferenciaEntity.getNomeOperadorTransferencia());
    }

    @Test
    public void testGetUserById_NonExistentUser() {
        Long nonExistentTransferenciaId = 100L;
        when(transferenciaRepository.findById(nonExistentTransferenciaId)).thenReturn(Optional.empty());

        assertThrows(TransferenciaNotFoundException.class, () -> transferenciaService.getTransferenciaById(nonExistentTransferenciaId));
    }
}

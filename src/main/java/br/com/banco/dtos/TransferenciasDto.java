package br.com.banco.dtos;

import java.util.List;

import javax.validation.constraints.NotBlank;

import br.com.banco.entities.TransferenciaEntity;
import lombok.Data;

@Data
public class TransferenciasDto {

    @NotBlank
    private List<TransferenciaEntity> transferencias;

    @NotBlank
    private double saldoTotal = 0;

    @NotBlank
    private double saldoPeriodo = 0;
}

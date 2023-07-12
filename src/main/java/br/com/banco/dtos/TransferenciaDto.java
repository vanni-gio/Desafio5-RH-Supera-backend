package br.com.banco.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import br.com.banco.entities.ContaEntity;
import br.com.banco.types.TipoTransacao;
import lombok.Data;

@Data
public class TransferenciaDto {

    @NotBlank
    private LocalDateTime dataTransferencia;

    @NotBlank
    private double valor;

    @NotBlank
    private TipoTransacao tipo;

    @NotBlank
    private String nomeOperadorTransacao;

    @NotBlank
    private ContaEntity conta;
}

package br.com.banco.dtos;


import java.util.Date;

import javax.validation.constraints.NotBlank;

import br.com.banco.types.TipoTransacao;
import lombok.Data;

@Data
public class TransferenciaDto {

    @NotBlank
    private Date dataTransferencia;

    @NotBlank
    private double valor;

    @NotBlank
    private TipoTransacao tipo;

    
    private String nomeOperadorTransferencia;
}

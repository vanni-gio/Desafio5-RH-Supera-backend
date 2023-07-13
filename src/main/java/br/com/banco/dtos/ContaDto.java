package br.com.banco.dtos;

import javax.validation.constraints.NotBlank;


import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class ContaDto {
    @NotBlank
    @CPF
    private String cpf;


    @NotBlank
    private String nomeResponsavel;
}

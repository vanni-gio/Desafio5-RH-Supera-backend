package br.com.banco.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class ContaDto {
    @NotBlank
    @Size(max = 11)
    @CPF
    private String cpf;

    @NotBlank
    private String senha;
}

package br.com.banco.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Data
@Table(name = "conta")
public class ContaEntity {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Long id_conta;
    
    @Column(nullable = false, name = "saldo_total")
    private Double saldoTotal;

    @Column(nullable = false, name = "nro_conta")
    private Long nroConta;

    @Column(nullable = false, length = 11, name = "cpf")
    private String cpf;

    @Column(length = 50, name = "nome_responsavel")
    private String nomeResponsavel;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<TransferenciaEntity> transferencias = new ArrayList<>();
}

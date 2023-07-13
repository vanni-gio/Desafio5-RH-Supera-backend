package br.com.banco.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Table;


import br.com.banco.types.TipoTransacao;
import lombok.Data;




@Entity
@Data
@Table(name = "transferencia")
public class TransferenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "data_transferencia")
    private LocalDateTime dataTransferencia;
    
    @Column(nullable = false, name="valor")
    private double valor;

    @Column(nullable = false, name="tipo")
    private TipoTransacao tipo;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false, name="nome_operador_transacao")
    private String nomeOperadorTransacao;

    @ManyToOne(
        cascade = CascadeType.ALL
    )
    private ContaEntity conta;

    //standard constructors, getters, setters
}

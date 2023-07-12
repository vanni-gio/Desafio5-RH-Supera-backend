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
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "transferencia")
public class TransferenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataTransferencia;
    
    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private String tipo;

    @Enumerated(EnumType.ORDINAL)
    private TipoTransacao nomeOperadorTransacao;

    @Column(nullable = false)
    private Integer contaId;

    @ManyToOne(
        cascade = CascadeType.ALL
    )
    private ContaEntity conta;

    //standard constructors, getters, setters
}

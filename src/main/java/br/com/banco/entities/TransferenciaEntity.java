package br.com.banco.entities;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataTransferencia;
    
    @Column(nullable = false, name="valor")
    private double valor;

    @Column(nullable = false, name="tipo")
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;
    
    
    @Column(nullable = false, name="nome_operador_transacao")
    private String nomeOperadorTransferencia;

    @ManyToOne(
        cascade = CascadeType.ALL
    )
    private ContaEntity conta;

    //standard constructors, getters, setters
}

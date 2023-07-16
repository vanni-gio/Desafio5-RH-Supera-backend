package br.com.banco.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import br.com.banco.types.TipoTransacao;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;




@Entity
@Data
@Table(name = "transferencia")
@RequiredArgsConstructor
public class TransferenciaEntity {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @Column(nullable = false, name = "data_transferencia")
    @Temporal(TemporalType.TIMESTAMP)
    @NonNull
    private Date dataTransferencia;
    
    @Column(nullable = false, name="valor")
    @NonNull
    private Double valor;

    @Column(nullable = false, name="tipo")
    @Enumerated(EnumType.STRING)
    @NonNull
    private TipoTransacao tipo;
    
    @Column(nullable = false, name="nome_operador_transacao")
    @NonNull
    private String nomeOperadorTransferencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    private ContaEntity conta;

    //standard constructors, getters, setters
}

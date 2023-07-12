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

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "conta")
public class ContaEntity {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Double saldoTotal;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, length = 50)
    private String nomeResponsavel;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<TransferenciaEntity> transferencias = new ArrayList<>();
}

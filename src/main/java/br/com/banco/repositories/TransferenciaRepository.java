package br.com.banco.repositories;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.entities.TransferenciaEntity;

public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Long> {
    public List<TransferenciaEntity> findAllByNomeOperadorTransferencia(String nomeOperadorTransferencia);
    public List<TransferenciaEntity> findAllByDataTransferenciaBetween(Date dataTransferenciaStart, Date dataTransferenciaEnd);
    public List<TransferenciaEntity> findAllByNomeOperadorTransferenciaAndDataTransferenciaBetween(
        String nomeOperadorTransferencia,
        Date dataTransferenciaStart,
        Date dataTransferenciaEnd
    );
 }
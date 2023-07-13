package br.com.banco.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.entities.TransferenciaEntity;

public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Long> {
    public List<TransferenciaEntity> findAllByNomeOperadorTransferencia();
    public List<TransferenciaEntity> findAllByDataTransferenciaBetween();
 }
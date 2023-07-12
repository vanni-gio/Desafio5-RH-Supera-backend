package br.com.banco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.entities.TransferenciaEntity;

public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Long> { }
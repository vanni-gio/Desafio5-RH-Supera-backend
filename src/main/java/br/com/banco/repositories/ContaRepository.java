package br.com.banco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.entities.ContaEntity;

public interface ContaRepository extends JpaRepository<ContaEntity, Long> { }
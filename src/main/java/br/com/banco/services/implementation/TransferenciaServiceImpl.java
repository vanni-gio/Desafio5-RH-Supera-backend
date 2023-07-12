package br.com.banco.services.implementation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.banco.entities.TransferenciaEntity;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.services.interfaces.TransferenciaServiceInterface;

public class TransferenciaServiceImpl implements TransferenciaServiceInterface{
    @Autowired
    TransferenciaRepository transferenciaRepository;

    @Transactional
    public TransferenciaEntity save(TransferenciaEntity transferencia){
        return transferenciaRepository.save(transferencia);
    }

}

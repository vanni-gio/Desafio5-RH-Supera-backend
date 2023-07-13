package br.com.banco.services.implementation;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.entities.TransferenciaEntity;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.services.interfaces.TransferenciaServiceInterface;

@Service
public class TransferenciaServiceImpl implements TransferenciaServiceInterface{
    @Autowired
    TransferenciaRepository transferenciaRepository;

    @Transactional
    public TransferenciaEntity save(TransferenciaEntity transferencia){
        return transferenciaRepository.save(transferencia);
    }

    public Optional<TransferenciaEntity> getOneTransferencia(Long id_transferencia) {
        return transferenciaRepository.findById(id_transferencia);
    }

    public List<TransferenciaEntity> getAll() {
        return transferenciaRepository.findAll();
    }

    public List<TransferenciaEntity> getAllByNomeOperadorTransferencia(String nomeOperadorTransferencia) {
        return transferenciaRepository.findAllByNomeOperadorTransferencia(nomeOperadorTransferencia);
    }

    public List<TransferenciaEntity> getAllByDataTransferenciaBetween(Date dataStart, Date dataEnd) {
        return transferenciaRepository.findAllByDataTransferenciaBetween(dataStart, dataEnd);
    }

    public List<TransferenciaEntity> getAllByNomeOperadorTransferenciaAndDataTransferenciaBetween(
        String nomeOperadorTransferencia,
        Date dataTransferenciaStart,
        Date dataTransferenciaEnd
    ) {

        return transferenciaRepository.findAllByNomeOperadorTransferenciaAndDataTransferenciaBetween(
            nomeOperadorTransferencia,
            dataTransferenciaStart,
            dataTransferenciaEnd);
    }

}

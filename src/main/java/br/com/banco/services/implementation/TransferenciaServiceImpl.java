package br.com.banco.services.implementation;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.entities.ContaEntity;
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

    
    public List<TransferenciaEntity> filterTransferenciasConta(
        ContaEntity conta,
        Date dataTransferenciaStart,
        Date dataTransferenciaEnd,
        String nomeOperador
    ){
        var transferencias = conta.getTransferencias();
        List<TransferenciaEntity> filteredList = new ArrayList<>();
        filteredList = transferencias.stream()
            .filter(transferencia -> filterDataAndNomeOperador(transferencia, nomeOperador, dataTransferenciaStart, dataTransferenciaEnd))
            .collect(Collectors.toList());

        return filteredList;
    }

    public static boolean filterDataAndNomeOperador(TransferenciaEntity transferencia, String nomeOperador, Date start, Date end){
        var nomeOperadorTransferencia = transferencia.getNomeOperadorTransferencia();
        if(start != null && end != null){
            // verifica se uma data está entre duas outras datas
            Predicate<Date> datePredicated = (t) -> start.compareTo(t) * t.compareTo(end) >= 0;
            if(nomeOperador == null)
                return datePredicated.test(transferencia.getDataTransferencia());
            else{
                if(nomeOperadorTransferencia != null)
                    return datePredicated.test(transferencia.getDataTransferencia()) && nomeOperadorTransferencia.equals(nomeOperador);
            }
        }else if(nomeOperador != null){
            if(nomeOperadorTransferencia != null)
                return nomeOperadorTransferencia.equals(nomeOperador);
        }
        return false;
    }
}

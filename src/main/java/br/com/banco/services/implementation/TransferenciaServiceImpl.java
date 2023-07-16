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

import br.com.banco.dtos.TransferenciasDto;
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

    public TransferenciasDto getAll() {
        var list = transferenciaRepository.findAll();
        TransferenciasDto newTransferenciasDto = getTransferenciasAsDto(null, null,null, list);
        return newTransferenciasDto;
    }

    public TransferenciasDto getAllByNomeOperadorTransferencia(String nomeOperadorTransferencia) {
        var list = transferenciaRepository.findAllByNomeOperadorTransferencia(nomeOperadorTransferencia);
        TransferenciasDto newTransferenciasDto = getTransferenciasAsDto(null, null, nomeOperadorTransferencia, list);
        return newTransferenciasDto;
    }

    public TransferenciasDto getAllByDataTransferenciaBetween(Date dataStart, Date dataEnd) {
        var list = transferenciaRepository.findAllByDataTransferenciaBetween(dataStart, dataEnd);
        TransferenciasDto newTransferenciasDto = getTransferenciasAsDto(dataStart, dataEnd, null, list);
        return newTransferenciasDto;
    }

    public TransferenciasDto getAllByNomeOperadorTransferenciaAndDataTransferenciaBetween(
        String nomeOperadorTransferencia,
        Date dataStart,
        Date dataEnd
    ) {

        List<TransferenciaEntity> list = transferenciaRepository.findAllByNomeOperadorTransferenciaAndDataTransferenciaBetween(
            nomeOperadorTransferencia,
            dataStart,
            dataEnd);
        TransferenciasDto newTransferenciasDto = getTransferenciasAsDto(dataStart, dataEnd, nomeOperadorTransferencia, list);
        return newTransferenciasDto;
    }

    private TransferenciasDto getTransferenciasAsDto(
            Date dataInicio,
            Date dataFim,
            String nomeOperador,
            List<TransferenciaEntity> list
        ) {
        
        TransferenciasDto newTransferenciasDto = new TransferenciasDto();
        double saldoTotal = transferenciaRepository.sumSaldoTotal();
        double saldoPeriodo = 0;
        for (TransferenciaEntity t : list) {
            if(dataFim != null && dataInicio != null){
                var dataTransferencia = t.getDataTransferencia();
                if(dataTransferencia.compareTo(dataFim) < 0 && dataTransferencia.compareTo(dataInicio) >= 0)
                    saldoPeriodo += t.getValor();
                
            }else if(nomeOperador != null){
                saldoPeriodo += t.getValor();
            }
        }
        newTransferenciasDto.setTransferencias(list);
        newTransferenciasDto.setSaldoTotal(saldoTotal);
        newTransferenciasDto.setSaldoPeriodo(saldoPeriodo);
        return newTransferenciasDto;
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


    
    public Optional<TransferenciaEntity> getTransferenciaById(Long transferenciaId) throws TransferenciaNotFoundException {
        return transferenciaRepository.findById(transferenciaId);
    }
}

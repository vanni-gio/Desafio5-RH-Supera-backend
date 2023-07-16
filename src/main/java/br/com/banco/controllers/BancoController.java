package br.com.banco.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dtos.TransferenciasDto;
import br.com.banco.entities.ContaEntity;
import br.com.banco.services.implementation.ContaServiceImpl;
import br.com.banco.services.implementation.TransferenciaServiceImpl;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/")
public class BancoController {

    @Autowired
    private ContaServiceImpl _contaService;

    @Autowired
    private TransferenciaServiceImpl _transferenciaService;

    @GetMapping("/transferencias")
    public ResponseEntity<Object> getAllTransferencias(
        @RequestParam(required = false) Date dataInicial,
        @RequestParam(required = false) Date dataFinal,
        @RequestParam(required = false) String nomeOperador
    )
    {
        TransferenciasDto transferencias = _transferenciaService.getAll();
        boolean isDataInicialNull = nullOrEmpty(dataInicial);
        boolean isDataFinalNull = nullOrEmpty(dataFinal);
        boolean isNomeOperadorEmptyOrNull = nullOrEmpty(nomeOperador);

        if(isDataFinalNull && isDataInicialNull){
            if(!isNomeOperadorEmptyOrNull)
                transferencias = _transferenciaService.getAllByNomeOperadorTransferencia(nomeOperador);
            
        }else if(!isDataFinalNull && !isDataInicialNull){
                if(!isNomeOperadorEmptyOrNull)
                    transferencias = _transferenciaService.getAllByNomeOperadorTransferenciaAndDataTransferenciaBetween(
                    nomeOperador, dataInicial, dataFinal);
                else
                    transferencias = _transferenciaService.getAllByDataTransferenciaBetween(dataInicial, dataFinal);     
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Os campos data inicial e final não devem ser nulos");
        }

       return ResponseEntity.status(HttpStatus.OK).body(transferencias);
    }


    @GetMapping("/contas")
    public ResponseEntity<Object> getAllContas(
        @RequestParam(required = false) Date dataInicial,
        @RequestParam(required = false) Date dataFinal,
        @RequestParam(required = false) String nomeOperador
    ){
        List<ContaEntity> contas = _contaService.getAll();
        boolean isDataInicialNull = nullOrEmpty(dataInicial);
        boolean isDataFinalNull = nullOrEmpty(dataFinal);
        boolean isNomeOperadorEmptyOrNull = nullOrEmpty(nomeOperador);


        if(isDataFinalNull && isDataInicialNull){
            if(!isNomeOperadorEmptyOrNull)
                for (ContaEntity contaEntity : contas) {
                    var filteredTransferencias = _transferenciaService.filterTransferenciasConta(contaEntity, dataInicial, dataFinal, nomeOperador);
                    contaEntity.setTransferencias(filteredTransferencias);
                }      
        }else if(!isDataFinalNull && !isDataInicialNull){
            for (ContaEntity contaEntity : contas) {
                var filteredTransferencias = _transferenciaService.filterTransferenciasConta(contaEntity, dataInicial, dataFinal, nomeOperador);
                contaEntity.setTransferencias(filteredTransferencias);
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Os campos data inicial e data final são mutualmente necessários!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contas);
    }

    public static boolean nullOrEmpty(Object obj){
        return (obj == null || (obj instanceof String && obj.toString().isBlank()));
    }

    
}

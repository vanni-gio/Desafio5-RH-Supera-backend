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

import br.com.banco.entities.ContaEntity;
import br.com.banco.entities.TransferenciaEntity;
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
        List<TransferenciaEntity> transferencias = null;
        if(!nullOrEmpty(dataInicial) && !nullOrEmpty(dataFinal)){
            if(!nullOrEmpty(nomeOperador)){
                transferencias = _transferenciaService.getAllByNomeOperadorTransferenciaAndDataTransferenciaBetween(
                    nomeOperador, dataInicial, dataFinal
                    );
            }else{
                transferencias = _transferenciaService.getAllByDataTransferenciaBetween(dataInicial, dataFinal);
            }
        }else{
            if(!nullOrEmpty(nomeOperador)){
                transferencias = _transferenciaService.getAllByNomeOperadorTransferencia(nomeOperador);
            }else if(!(nullOrEmpty(dataInicial) && nullOrEmpty(dataFinal))){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Os campos data inicial e final não devem ser nulos");
            }else{
                transferencias = _transferenciaService.getAll();
            }
        }

       return ResponseEntity.status(HttpStatus.OK).body(transferencias);
    }


    @GetMapping("/contas")
    public ResponseEntity<List<ContaEntity>> getAllContas(
        @RequestParam(required = false) Date dataInicial,
        @RequestParam(required = false) Date dataFinal,
        @RequestParam(required = false) String nomeOperador
    ){
        var contas = _contaService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(contas);
    }

    public static boolean nullOrEmpty(Object obj){
        return (obj == null || (obj instanceof String && obj.toString().isBlank()));
    }

    
}

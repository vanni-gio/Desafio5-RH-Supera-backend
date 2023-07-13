package br.com.banco.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("conta/{id}")
    public ResponseEntity<Object> getOneConta(@PathVariable(value = "id") Long id)
    {
        var contaOptional = _contaService.findById(id);
        if(!contaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(contaOptional.get());
    }

    @GetMapping("/transferencias")
    public ResponseEntity<Object> getAllTransferencias(
        @RequestParam(required = false) Date dataInicial,
        @RequestParam(required = false) Date dataFinal,
        @RequestParam(required = false) String nomeOperador
    )
    {
        List<TransferenciaEntity> transferencias = null;
        if(dataInicial != null && dataFinal != null){
            if(nomeOperador != null){
                transferencias = _transferenciaService.getAllByNomeOperadorTransferenciaAndDataTransferenciaBetween(
                    nomeOperador, dataInicial, dataFinal
                    );
            }else{
                transferencias = _transferenciaService.getAllByDataTransferenciaBetween(dataInicial, dataFinal);
            }
        }else{
            if(nomeOperador != null){
                transferencias = _transferenciaService.getAllByNomeOperadorTransferencia(nomeOperador);
            }else if(!(dataInicial == null && dataFinal == null)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Os campos data inicial e final não devem ser nulos");
            }else{
                transferencias = _transferenciaService.getAll();
            }
        }

       return ResponseEntity.status(HttpStatus.OK).body(transferencias);
    }


}

package br.com.banco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.banco.services.implementation.ContaServiceImpl;
import br.com.banco.services.implementation.TransferenciaServiceImpl;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/")
public class BancoController {

    @Autowired
    private ContaServiceImpl _contaService;
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
    public ResponseEntity<Object> getAllTransferencias()
    {
        var transferencias = _transferenciaService.getAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(transferencias);
    }




}

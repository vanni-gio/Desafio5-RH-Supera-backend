package br.com.banco.controllers;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.BeanUtils;
import br.com.banco.dtos.ContaDto;
import br.com.banco.dtos.TransferenciaDto;
import br.com.banco.entities.ContaEntity;
import br.com.banco.entities.TransferenciaEntity;
import br.com.banco.services.implementation.ContaServiceImpl;
import br.com.banco.services.implementation.TransferenciaServiceImpl;
import br.com.banco.types.TipoTransacao;

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

package br.com.banco.controllers;

import java.util.List;

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
import br.com.banco.entities.ContaEntity;
import br.com.banco.services.implementation.ContaServiceImpl;
import br.com.banco.services.implementation.TransferenciaServiceImpl;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/conta")
public class BancoController {

    @Autowired
    private ContaServiceImpl _contaService;
    private TransferenciaServiceImpl _transferenciaService;

    @PostMapping
    public ResponseEntity<Object> saveConta(@Valid @RequestBody ContaDto contaDto)
    {
        if(_contaService.existsByCpf(contaDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: CPF já cadastrado!");
        }
        var contaEntity = new ContaEntity();
        BeanUtils.copyProperties(contaDto, contaEntity);
        contaEntity.setSaldoTotal(500.0); 
        return ResponseEntity.status(HttpStatus.CREATED).body(_contaService.save(contaEntity));
    }

    @GetMapping
    public ResponseEntity<List<ContaEntity>> getAllContas()
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(_contaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneConta(@PathVariable(value = "id") Long id)
    {
        var contaOptional = _contaService.findById(id);
        if(!contaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(contaOptional.get());
    }


}

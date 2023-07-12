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

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaServiceImpl _contaService;

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


    // @RequestMapping(value = "/conta/{id}", method =  RequestMethod.PUT)
    // public ResponseEntity<ContaEntity> Put(@PathVariable(value = "id") long id, @Validated @RequestBody ContaEntity newConta)
    // {
    //     Optional<ContaEntity> oldConta = _contaRepository.findById(id);
    //     if(oldConta.isPresent()){
    //         ContaEntity conta = oldConta.get();
    //         conta.setSenha(newConta.getSenha());
    //         _contaRepository.save(conta);
    //         return new ResponseEntity<ContaEntity>(conta, HttpStatus.OK);
    //     }
    //     else
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }

    // @RequestMapping(value = "/conta/{id}", method = RequestMethod.DELETE)
    // public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    // {
    //     Optional<ContaEntity> conta = _contaRepository.findById(id);
    //     if(conta.isPresent()){
    //         _contaRepository.delete(conta.get());
    //         return new ResponseEntity<>(HttpStatus.OK);
    //     }
    //     else
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
}

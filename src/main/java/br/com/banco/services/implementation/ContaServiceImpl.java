
package br.com.banco.services.implementation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.entities.ContaEntity;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.services.interfaces.ContaServiceInterface;

/**
 * ContaService
 */
@Service
public class ContaServiceImpl implements ContaServiceInterface{

    @Autowired
    ContaRepository contaRepository;

    @Transactional
    public ContaEntity save(ContaEntity conta){
        return contaRepository.save(conta);
    }

    public boolean existsByCpf(String cpf) {
        return contaRepository.existsByCpf(cpf);
    }

    public List<ContaEntity> findAll(){
        return contaRepository.findAll();
    }

    public Optional<ContaEntity> findById(Long id){
        return contaRepository.findById(id);
    }

}
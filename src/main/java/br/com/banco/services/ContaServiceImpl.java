
package br.com.banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.repositories.ContaRepository;

/**
 * ContaService
 */
@Service
public class ContaServiceImpl {

    @Autowired
    ContaRepository contaRepository;
    
}
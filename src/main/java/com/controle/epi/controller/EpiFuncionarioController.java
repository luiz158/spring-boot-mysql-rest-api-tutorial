package com.controle.epi.controller;

import com.controle.epi.exception.ResourceNotFoundException;
import com.controle.epi.model.Epi;
import com.controle.epi.model.EpiFuncionario;
import com.controle.epi.model.EpiFuncionarioRequest;
import com.controle.epi.model.Funcionario;
import com.controle.epi.repository.EpiFuncionarioRepository;
import com.controle.epi.repository.EpiRepository;
import com.controle.epi.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EpiFuncionarioController {

    @Autowired
    EpiFuncionarioRepository epiFuncionarioRepository;
    @Autowired
    EpiRepository epiRepository;
    @Autowired
    FuncionarioRepository funcionarioRepository;

    @GetMapping("/epi_funcionario")
    public List<EpiFuncionario> getAllEpiSetor() {
        return epiFuncionarioRepository.findAll();
    }

    @PostMapping("/epi_funcionario")
    public EpiFuncionario createEpiSetor(@Valid @RequestBody EpiFuncionarioRequest epiFuncionarioRequest) {
        EpiFuncionario epiFuncionario = new EpiFuncionario();
        
        Funcionario funcionario = funcionarioRepository.findById(epiFuncionarioRequest.getFuncionarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario", "id", epiFuncionarioRequest.getFuncionarioId()));
        Epi epi = epiRepository.findById(epiFuncionarioRequest.getEpiId())
                .orElseThrow(() -> new ResourceNotFoundException("Epi", "id", epiFuncionarioRequest.getEpiId()));
        
        epiFuncionario.setResponsavel(funcionario);
        epiFuncionario.setEpi(epi);
        epiFuncionario.setValidade(epiFuncionarioRequest.getValidade());
        
        return epiFuncionarioRepository.save(epiFuncionario);
    }

    @GetMapping("/epi_funcionario/{id}")
    public EpiFuncionario getEpiSetorById(@PathVariable(value = "id") Long epiFuncionarioId) {
        return epiFuncionarioRepository.findById(epiFuncionarioId)
                .orElseThrow(() -> new ResourceNotFoundException("EpiFuncionario", "id", epiFuncionarioId));
    }

    @PutMapping("/epi_funcionario/{id}")
    public EpiFuncionario updateEpiSetor(@PathVariable(value = "id") Long epiFuncionarioId,
                                           @Valid @RequestBody EpiFuncionarioRequest epiFuncionarioRequest) {

        EpiFuncionario epiFuncionario = epiFuncionarioRepository.findById(epiFuncionarioId)
                .orElseThrow(() -> new ResourceNotFoundException("EpiFuncionario", "id", epiFuncionarioId));

        Funcionario funcionario = funcionarioRepository.findById(epiFuncionarioRequest.getFuncionarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario", "id", epiFuncionarioRequest.getFuncionarioId()));
        Epi epi = epiRepository.findById(epiFuncionarioRequest.getEpiId())
                .orElseThrow(() -> new ResourceNotFoundException("Epi", "id", epiFuncionarioRequest.getEpiId()));
        
        epiFuncionario.setResponsavel(funcionario);
        epiFuncionario.setEpi(epi);
        epiFuncionario.setValidade(epiFuncionarioRequest.getValidade());
        
        return epiFuncionarioRepository.save(epiFuncionario);
    }

    @DeleteMapping("/epi_funcionario/{id}")
    public ResponseEntity<?> deleteEpiSetor(@PathVariable(value = "id") Long epiFuncionarioId) {
         EpiFuncionario epiFuncionario = epiFuncionarioRepository.findById(epiFuncionarioId)
                .orElseThrow(() -> new ResourceNotFoundException("EpiFuncionario", "id", epiFuncionarioId));

        epiFuncionarioRepository.delete(epiFuncionario);

        return ResponseEntity.ok().build();
    }
}

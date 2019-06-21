package com.controle.epi.controller;

import com.controle.epi.exception.ResourceNotFoundException;
import com.controle.epi.model.Epi;
import com.controle.epi.model.EpiSetor;
import com.controle.epi.model.Funcionario;
import com.controle.epi.model.Setor;
import com.controle.epi.model.SetorRequest;
import com.controle.epi.repository.EpiRepository;
import com.controle.epi.repository.EpiSetorRepository;
import com.controle.epi.repository.FuncionarioRepository;
import com.controle.epi.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SetorController {

    @Autowired
    SetorRepository setorRepository;
    @Autowired
    EpiRepository epiRepository;
    @Autowired
    EpiSetorRepository epiSetorRepository;
    @Autowired
    FuncionarioRepository funcionarioRepository;

    @GetMapping("/setor")
    public List<Setor> getAllSetores() {
        return setorRepository.findAll();
    }

    @PostMapping("/setor")
    public Setor createSetor(@Valid @RequestBody SetorRequest setorRequest) {
        Setor setor = new Setor();
        
        Funcionario responsavel = funcionarioRepository.findById(setorRequest.getResponsavelId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario", "id", setorRequest.getResponsavelId()));
        
        setor.setDescricao(setorRequest.getDescricao());
        setor.setResponsavel(responsavel);
        
        Setor result = setorRepository.save(setor);
        
        for(Long epiId: setorRequest.getEpis()){
            Epi epi = epiRepository.findById(epiId)
                .orElseThrow(() -> new ResourceNotFoundException("Epi", "id", epiId));
            
            EpiSetor epiSetor = new EpiSetor();
            epiSetor.setSetor(result.getIdSetor());
            epiSetor.setEpi(epi);
            epiSetorRepository.save(epiSetor);
        }
        
        return result;
    }

    @GetMapping("/setor/{id}")
    public Setor getSetorById(@PathVariable(value = "id") Long setorId) {
        return setorRepository.findById(setorId)
                .orElseThrow(() -> new ResourceNotFoundException("Setor", "id", setorId));
    }

    @PutMapping("/setor/{id}")
    public Setor updateSetor(@PathVariable(value = "id") Long setorId,
                                           @Valid @RequestBody SetorRequest setorRequest) {

        Setor setor = setorRepository.findById(setorId)
                .orElseThrow(() -> new ResourceNotFoundException("Setor", "id", setorId));

        Funcionario responsavel = funcionarioRepository.findById(setorRequest.getResponsavelId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario", "id", setorRequest.getResponsavelId()));
        
        setor.setDescricao(setorRequest.getDescricao());
        setor.setResponsavel(responsavel);
        
        Setor result = setorRepository.save(setor);
                
        for(EpiSetor epiSetor: setor.getEpis()){
            epiSetorRepository.delete(epiSetor);
        }
        
        for(Long epiId: setorRequest.getEpis()){
            Epi epi = epiRepository.findById(epiId)
                .orElseThrow(() -> new ResourceNotFoundException("Epi", "id", epiId));
            
            EpiSetor epiSetor = new EpiSetor();
            epiSetor.setSetor(result.getIdSetor());
            epiSetor.setEpi(epi);
            epiSetorRepository.save(epiSetor);
        }
        
        return result;
    }

    @DeleteMapping("/setor/{id}")
    public ResponseEntity<?> deleteSetor(@PathVariable(value = "id") Long setorId) {
        Setor setor = setorRepository.findById(setorId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", setorId));
        
        for(EpiSetor epiSetor: setor.getEpis()){
            epiSetorRepository.delete(epiSetor);
        }

        setorRepository.delete(setor);

        return ResponseEntity.ok().build();
    }
}

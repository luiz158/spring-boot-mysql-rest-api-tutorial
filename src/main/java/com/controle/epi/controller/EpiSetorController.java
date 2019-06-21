package com.controle.epi.controller;

import com.controle.epi.exception.ResourceNotFoundException;
import com.controle.epi.model.Epi;
import com.controle.epi.model.EpiSetor;
import com.controle.epi.model.EpiSetorRequest;
import com.controle.epi.model.Setor;
import com.controle.epi.repository.EpiRepository;
import com.controle.epi.repository.EpiSetorRepository;
import com.controle.epi.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EpiSetorController {

    @Autowired
    EpiSetorRepository epiSetorRepository;
    @Autowired
    SetorRepository setorRepository;
    @Autowired
    EpiRepository epiRepository;

    @GetMapping("/epi_setor")
    public List<EpiSetor> getAllEpiSetor() {
        return epiSetorRepository.findAll();
    }

    @PostMapping("/epi_setor")
    public EpiSetor createEpiSetor(@Valid @RequestBody EpiSetorRequest epiSetorRequest) {
        EpiSetor epiSetor = new EpiSetor();
        
        Setor setor = setorRepository.findById(epiSetorRequest.getSetorId())
                .orElseThrow(() -> new ResourceNotFoundException("Setor", "id", epiSetorRequest.getSetorId()));
        Epi epi = epiRepository.findById(epiSetorRequest.getEpiId())
                .orElseThrow(() -> new ResourceNotFoundException("Epi", "id", epiSetorRequest.getEpiId()));
        
        //epiSetor.setSetor(setor);
        epiSetor.setEpi(epi);
        
        return epiSetorRepository.save(epiSetor);
    }

    @GetMapping("/epi_setor/{id}")
    public EpiSetor getEpiSetorById(@PathVariable(value = "id") Long epiSetorId) {
        return epiSetorRepository.findById(epiSetorId)
                .orElseThrow(() -> new ResourceNotFoundException("EpiSetor", "id", epiSetorId));
    }

    @PutMapping("/epi_setor/{id}")
    public EpiSetor updateEpiSetor(@PathVariable(value = "id") Long epiSetorId,
                                           @Valid @RequestBody EpiSetorRequest epiSetorRequest) {

        EpiSetor epiSetor = epiSetorRepository.findById(epiSetorId)
                .orElseThrow(() -> new ResourceNotFoundException("EpiSetor", "id", epiSetorId));

        Setor setor = setorRepository.findById(epiSetorRequest.getSetorId())
                .orElseThrow(() -> new ResourceNotFoundException("Setor", "id", epiSetorRequest.getSetorId()));
        Epi epi = epiRepository.findById(epiSetorRequest.getEpiId())
                .orElseThrow(() -> new ResourceNotFoundException("Epi", "id", epiSetorRequest.getSetorId()));
        
        //epiSetor.setSetor(setor);
        epiSetor.setEpi(epi);
        
        return epiSetorRepository.save(epiSetor);
    }

    @DeleteMapping("/epi_setor/{id}")
    public ResponseEntity<?> deleteEpiSetor(@PathVariable(value = "id") Long epiSetorId) {
        EpiSetor epiSetor = epiSetorRepository.findById(epiSetorId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", epiSetorId));

        epiSetorRepository.delete(epiSetor);

        return ResponseEntity.ok().build();
    }
}

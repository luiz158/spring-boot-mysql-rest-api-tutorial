package com.controle.epi.controller;

import com.controle.epi.exception.ResourceNotFoundException;
import com.controle.epi.model.Epi;
import com.controle.epi.model.EpiFuncionario;
import com.controle.epi.model.EpiSetor;
import com.controle.epi.repository.EpiFuncionarioRepository;
import com.controle.epi.repository.EpiRepository;
import com.controle.epi.repository.EpiSetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EpiController {

    @Autowired
    EpiRepository epiRepository;
    @Autowired
    EpiSetorRepository epiSetorRepository;
    @Autowired
    EpiFuncionarioRepository epiFuncionarioRepository;

    @GetMapping("/epis")
    public List<Epi> getAllEpis() {
        return epiRepository.findAll();
    }

    @PostMapping("/epis")
    public Epi createEpi(@Valid @RequestBody Epi epi) {
        return epiRepository.save(epi);
    }

    @GetMapping("/epis/{id}")
    public Epi getEpiById(@PathVariable(value = "id") Long epiId) {
        return epiRepository.findById(epiId)
                .orElseThrow(() -> new ResourceNotFoundException("Epi", "id", epiId));
    }

    @PutMapping("/epis/{id}")
    public Epi updateEpi(@PathVariable(value = "id") Long epiId,
                                           @Valid @RequestBody Epi epiDetails) {

        Epi epi = epiRepository.findById(epiId)
                .orElseThrow(() -> new ResourceNotFoundException("Epi", "id", epiId));

        epi.setNome(epiDetails.getNome());
        epi.setTipo(epiDetails.getTipo());
        
        return epiRepository.save(epi);
    }

    @DeleteMapping("/epis/{id}")
    public ResponseEntity<?> deleteEpi(@PathVariable(value = "id") Long epiId) {
        List<EpiFuncionario> epiFuncionarioList = epiFuncionarioRepository.findByEpi(epiId);
        for(EpiFuncionario epiFuncionario: epiFuncionarioList){
            epiFuncionarioRepository.delete(epiFuncionario);
        }
        
        List<EpiSetor> epiSetorList = epiSetorRepository.findByEpi(epiId);
        for(EpiSetor epiSetor: epiSetorList){
            epiSetorRepository.delete(epiSetor);
        }
        
        Epi epi = epiRepository.findById(epiId)
                .orElseThrow(() -> new ResourceNotFoundException("Epi", "id", epiId));
        
        epiRepository.delete(epi);

        return ResponseEntity.ok().build();
    }
}

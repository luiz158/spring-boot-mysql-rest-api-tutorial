package com.controle.epi.controller;

import com.controle.epi.exception.ResourceNotFoundException;
import com.controle.epi.model.EpiSetor;
import com.controle.epi.repository.EpiSetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class EpiSetorController {

    @Autowired
    EpiSetorRepository epiSetorRepository;

    @GetMapping("/epi_setor")
    public List<EpiSetor> getAllEpiSetor() {
        return epiSetorRepository.findAll();
    }

    @PostMapping("/epi_setor")
    public EpiSetor createEpiSetor(@Valid @RequestBody EpiSetor epiSetor) {
        return epiSetorRepository.save(epiSetor);
    }

    @GetMapping("/epi_setor/{id}")
    public EpiSetor getEpiSetorById(@PathVariable(value = "id") Long epiSetorId) {
        return epiSetorRepository.findById(epiSetorId)
                .orElseThrow(() -> new ResourceNotFoundException("EpiSetor", "id", epiSetorId));
    }

    @PutMapping("/epi_setor/{id}")
    public EpiSetor updateEpiSetor(@PathVariable(value = "id") Long epiSetorId,
                                           @Valid @RequestBody EpiSetor epiSetorDetails) {

        EpiSetor epiSetor = epiSetorRepository.findById(epiSetorId)
                .orElseThrow(() -> new ResourceNotFoundException("EpiSetor", "id", epiSetorId));

//        note.setTitle(noteDetails.getTitle());
//        note.setContent(noteDetails.getContent());

        EpiSetor updatedEpiSetor = epiSetorRepository.save(epiSetor);
        return updatedEpiSetor;
    }

    @DeleteMapping("/epi_setor/{id}")
    public ResponseEntity<?> deleteEpiSetor(@PathVariable(value = "id") Long epiSetorId) {
        EpiSetor epiSetor = epiSetorRepository.findById(epiSetorId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", epiSetorId));

        epiSetorRepository.delete(epiSetor);

        return ResponseEntity.ok().build();
    }
}

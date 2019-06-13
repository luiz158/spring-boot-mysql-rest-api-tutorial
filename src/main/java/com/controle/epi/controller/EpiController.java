package com.controle.epi.controller;

import com.controle.epi.exception.ResourceNotFoundException;
import com.controle.epi.model.Epi;
import com.controle.epi.repository.EpiRepository;
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
public class EpiController {

    @Autowired
    EpiRepository epiRepository;

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

//        note.setTitle(noteDetails.getTitle());
//        note.setContent(noteDetails.getContent());

        Epi updatedEpi = epiRepository.save(epi);
        return updatedEpi;
    }

    @DeleteMapping("/epis/{id}")
    public ResponseEntity<?> deleteEpi(@PathVariable(value = "id") Long epiId) {
        Epi epi = epiRepository.findById(epiId)
                .orElseThrow(() -> new ResourceNotFoundException("Epi", "id", epiId));

        epiRepository.delete(epi);

        return ResponseEntity.ok().build();
    }
}

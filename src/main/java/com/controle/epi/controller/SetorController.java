package com.controle.epi.controller;

import com.controle.epi.exception.ResourceNotFoundException;
import com.controle.epi.model.Setor;
import com.controle.epi.repository.NoteRepository;
import com.controle.epi.repository.SetorRepository;
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
public class SetorController {

    @Autowired
    SetorRepository setorRepository;

    @GetMapping("/setor")
    public List<Setor> getAllSetores() {
        return setorRepository.findAll();
    }

    @PostMapping("/setor")
    public Setor createNote(@Valid @RequestBody Setor setor) {
        return setorRepository.save(setor);
    }

    @GetMapping("/setor/{id}")
    public Setor getNoteById(@PathVariable(value = "id") Long setorId) {
        return setorRepository.findById(setorId)
                .orElseThrow(() -> new ResourceNotFoundException("Setor", "id", setorId));
    }

    @PutMapping("/setor/{id}")
    public Setor updateNote(@PathVariable(value = "id") Long setorId,
                                           @Valid @RequestBody Setor noteDetails) {

        Setor setor = setorRepository.findById(setorId)
                .orElseThrow(() -> new ResourceNotFoundException("Setor", "id", setorId));

        setor.setDescricao(noteDetails.getDescricao());
        setor.setResponsavel(noteDetails.getResponsavel());

        Setor updatedSetor = setorRepository.save(setor);
        return updatedSetor;
    }

    @DeleteMapping("/setor/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long setorId) {
        Setor setor = setorRepository.findById(setorId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", setorId));

        setorRepository.delete(setor);

        return ResponseEntity.ok().build();
    }
}

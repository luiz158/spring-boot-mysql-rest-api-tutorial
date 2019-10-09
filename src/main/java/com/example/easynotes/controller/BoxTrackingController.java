package com.example.easynotes.controller;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.BoxEvent;
import com.example.easynotes.repository.BoxEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by DeepankarShukla.
 */
@RestController
@RequestMapping("/api")
public class BoxTrackingController {

    @Autowired
    BoxEventRepository boxEventRepository;
    
    @Autowired
    BoxEventRepository boxRepository;
    
    @Autowired
    BoxEventRepository boxStateRepository;

    @GetMapping("/boxes")
    public List<BoxEvent> getAllNotes() {
        return boxEventRepository.findAll();
    }

    @PostMapping("/boxes")
    public BoxEvent createNote(@Valid @RequestBody BoxEvent boxEvent) {
        return boxEventRepository.save(boxEvent);
    }

    @GetMapping("/notes/{id}")
    public BoxEvent getNoteById(@PathVariable(value = "id") Long boxId) {
        return boxEventRepository.findById(boxId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", boxId));
    }

    @PutMapping("/notes/{id}")
    public BoxEvent updateNote(@PathVariable(value = "id") Long boxId,
                                           @Valid @RequestBody BoxEvent boxDetails) {

        BoxEvent boxEvent = boxEventRepository.findById(boxId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", boxId));
        

      return boxEventRepository.save(boxEvent);
      
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long boxId) {
        BoxEvent boxEvent = boxEventRepository.findById(boxId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", boxId));

        boxEventRepository.delete(boxEvent);

        return ResponseEntity.ok().build();
    }
}

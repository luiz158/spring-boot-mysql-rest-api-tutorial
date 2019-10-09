package com.example.easynotes.controller;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Box;
import com.example.easynotes.model.BoxEvent;
import com.example.easynotes.model.BoxState;
import com.example.easynotes.repository.BoxEventRepository;
import com.example.easynotes.repository.BoxRepository;
import com.example.easynotes.repository.BoxStateRepository;

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
    BoxRepository boxRepository;
    
    @Autowired
    BoxStateRepository boxStateRepository;

    @GetMapping("/boxes")
    public List<BoxEvent> getAllBoxEvents() {
        return boxEventRepository.findAll();
    }
    
    @GetMapping("/boxStates")
    public List<BoxState> getAllBoxStates() {
        return boxStateRepository.findAll();
    }

    @PostMapping("/boxes")
    public BoxEvent createBoxEvent(@Valid @RequestBody BoxEvent boxEvent) {
        return boxEventRepository.save(boxEvent);
    }
    
    @PostMapping("/boxState")
    public BoxState createBoxState(@Valid @RequestBody BoxState boxState) {
        return boxStateRepository.save(boxState);
    }
    
    @GetMapping("/box")
    public List<Box> getAllBoxes() {
        return boxRepository.findAll();
    }
    
    @PostMapping("/box")
    public Box createBox(@Valid @RequestBody Box box) {
        return boxRepository.save(box);
    }

    @GetMapping("/box/{id}")
    public Box getBoxById(@PathVariable(value = "id") Long boxId) {
        return boxRepository.findById(boxId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", boxId));
    }
    
    @GetMapping("/boxes/{id}")
    public BoxEvent getBoxEventById(@PathVariable(value = "id") Long boxId) {
        return boxEventRepository.findById(boxId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", boxId));
    }

    @PutMapping("/boxes/{id}")
    public BoxEvent updateBox(@PathVariable(value = "id") Long boxId,
                                           @Valid @RequestBody BoxEvent boxDetails) {

        BoxEvent boxEvent = boxEventRepository.findById(boxId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", boxId));
        

      return boxEventRepository.save(boxEvent);
      
    }

    @DeleteMapping("/boxes/{id}")
    public ResponseEntity<?> deleteBox(@PathVariable(value = "id") Long boxId) {
        BoxEvent boxEvent = boxEventRepository.findById(boxId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", boxId));

        boxEventRepository.delete(boxEvent);

        return ResponseEntity.ok().build();
    }
}

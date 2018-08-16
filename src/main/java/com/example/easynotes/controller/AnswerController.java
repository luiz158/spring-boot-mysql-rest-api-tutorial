package com.example.easynotes.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Answer;
import com.example.easynotes.repository.AnswerRepository;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class AnswerController {

    @Autowired
    AnswerRepository noteRepository;

    @GetMapping("/answers")
    public List<Answer> getAllAnswers() {
        return noteRepository.findAll();
    }

    @PostMapping("/answers")
    public Answer createAnswer(@Valid @RequestBody Answer note) {
        return noteRepository.save(note);
    }

    @GetMapping("/answers/{id}")
    public Answer getAnswerById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer", "id", noteId));
    }

    @PutMapping("/answers/{id}")
    public Answer updateAnswer(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody Answer noteDetails) {

        Answer note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer", "id", noteId));

        note.setAnswer(noteDetails.getAnswer());
        //note.setContent(noteDetails.getContent());

        Answer updatedAnswer = noteRepository.save(note);
        return updatedAnswer;
    }

    @DeleteMapping("/answers/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable(value = "id") Long noteId) {
        Answer note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Answer", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}

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
import com.example.easynotes.model.Category;
import com.example.easynotes.repository.CategoryRepository;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryRepository noteRepository;

    @GetMapping("/categories")
    public List<Category> getAllCategorys() {
        return noteRepository.findAll();
    }

    @PostMapping("/categories")
    public Category createCategory(@Valid @RequestBody Category note) {
        return noteRepository.save(note);
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", noteId));
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody Category noteDetails) {

        Category note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", noteId));

        note.setCatgoryName(noteDetails.getCatgoryName());
        //note.setContent(noteDetails.getContent());

        Category updatedCategory = noteRepository.save(note);
        return updatedCategory;
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(value = "id") Long noteId) {
        Category note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}

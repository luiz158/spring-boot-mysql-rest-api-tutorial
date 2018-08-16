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
import com.example.easynotes.model.Question;
import com.example.easynotes.repository.CategoryRepository;
import com.example.easynotes.repository.QuestionRepository;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;
    
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @PostMapping("/categories/{categoryId}/questions")
    public Question createQuestion(@Valid @RequestBody Question question,@PathVariable(value = "categoryId") Long categoryId) {
    	Category c=categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
    	question.setCategory(c);
        return questionRepository.save(question);
    }

    @GetMapping("/questions/{id}")
    public Question getQuestionById(@PathVariable(value = "id") Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", questionId));
    }

    @PutMapping("/questions/{id}")
    public Question updateQuestion(@PathVariable(value = "id") Long questionId,
                                           @Valid @RequestBody Question noteDetails) {

        Question note = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", questionId));

        note.setQuestion(noteDetails.getQuestion());
        //note.setContent(noteDetails.getContent());

        Question updatedQuestion = questionRepository.save(note);
        return updatedQuestion;
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable(value = "id") Long questionId) {
        Question note = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question", "id", questionId));

        questionRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}

package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.Question;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}

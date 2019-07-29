package com.example.easynotes.repository;

import com.example.easynotes.model.Note;
import com.example.easynotes.controller.NoteDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@Repository

public interface NoteRepository extends JpaRepository<Note, Long> , NoteRepositoryCustom {


}

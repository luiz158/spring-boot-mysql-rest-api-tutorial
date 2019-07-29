package com.example.easynotes.repository;

import com.example.easynotes.controller.NoteDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface NoteRepositoryCustom{

    List<NoteDTO> findAllCustom(Specification specification);
}

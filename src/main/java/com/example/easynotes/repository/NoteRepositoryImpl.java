package com.example.easynotes.repository;

import com.example.easynotes.controller.NoteDTO;
import com.example.easynotes.model.Note;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class NoteRepositoryImpl implements NoteRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<NoteDTO> findAllCustom(Specification specification) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<NoteDTO> cq = cb.createQuery(NoteDTO.class);
        Root<Note> root = cq.from(Note.class);

        cq.where(specification.toPredicate(root, cq, cb));
        cq.select(cb.construct(NoteDTO.class, root.get("id"), root.get("title"), root.get("content")));

        TypedQuery<NoteDTO> query = entityManager.createQuery(cq);
        List<NoteDTO> notes = query.getResultList();



        return notes;
    }
}

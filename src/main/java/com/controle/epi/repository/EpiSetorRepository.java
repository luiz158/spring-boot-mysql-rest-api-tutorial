package com.controle.epi.repository;

import com.controle.epi.model.EpiSetor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpiSetorRepository extends JpaRepository<EpiSetor, Long> {

}

package com.controle.epi.repository;

import com.controle.epi.model.EpiSetor;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EpiSetorRepository extends JpaRepository<EpiSetor, Long> {
    
    @Query(value = "SELECT * FROM epi_setor where setor = ?1", nativeQuery = true)
    List<EpiSetor> findBySetor(Long setorId);
    
}

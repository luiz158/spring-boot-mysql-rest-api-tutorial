package com.controle.epi.repository;

import com.controle.epi.model.EpiFuncionario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EpiFuncionarioRepository extends JpaRepository<EpiFuncionario, Long> {

    @Query(value = "SELECT * FROM epi_funcionario where epi = ?1", nativeQuery = true)
    List<EpiFuncionario> findByEpi(Long epiId);
    
    @Query(value = "SELECT * FROM epi_funcionario where responsavel = ?1", nativeQuery = true)
    List<EpiFuncionario> findByFuncionario(Long funcionarioId);
}

package com.controle.epi.repository;

import com.controle.epi.model.EpiFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpiFuncionarioRepository extends JpaRepository<EpiFuncionario, Long> {

}

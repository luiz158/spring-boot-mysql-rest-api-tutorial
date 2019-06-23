package com.controle.epi.repository;

import com.controle.epi.model.Funcionario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    
    @Query(value = "SELECT * FROM funcionario where email = ?1 and cpf = ?2 and administrador = 1", nativeQuery = true)
    List<Funcionario> login(String email, String cpf);
    
}

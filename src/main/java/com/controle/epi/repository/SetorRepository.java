package com.controle.epi.repository;

import com.controle.epi.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {

}

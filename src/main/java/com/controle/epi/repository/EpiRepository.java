package com.controle.epi.repository;

import com.controle.epi.model.Epi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@Repository
public interface EpiRepository extends JpaRepository<Epi, Long> {

}

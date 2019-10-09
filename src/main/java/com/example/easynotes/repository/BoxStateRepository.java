package com.example.easynotes.repository;


import com.example.easynotes.model.BoxState;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by DeepankarShukla.
 */

@Repository
public interface BoxStateRepository extends JpaRepository<BoxState, Long> {

}

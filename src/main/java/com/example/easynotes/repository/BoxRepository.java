package com.example.easynotes.repository;

import com.example.easynotes.model.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by DeepankarShukla.
 */

@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {

}

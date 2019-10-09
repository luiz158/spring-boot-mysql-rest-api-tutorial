package com.example.easynotes.repository;

import com.example.easynotes.model.BoxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by DeepankarShukla.
 */

@Repository
public interface BoxEventRepository extends JpaRepository<BoxEvent, Long> {

}

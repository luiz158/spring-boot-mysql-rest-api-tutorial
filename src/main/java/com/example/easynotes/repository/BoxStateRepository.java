package com.example.easynotes.repository;


import com.example.easynotes.model.BoxState;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by DeepankarShukla.
 */

@Repository
public interface BoxStateRepository extends JpaRepository<BoxState, Long> {
	
	 @Query("SELECT bs FROM BoxState as bs WHERE bs.insertionState like %:boxId%")
	 List<BoxState> findBoxState(@Param("boxId")String boxId); 

}

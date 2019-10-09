package com.example.easynotes.repository;

import com.example.easynotes.model.BoxEvent;
import com.example.easynotes.model.BoxState;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by DeepankarShukla.
 */

@Repository
public interface BoxEventRepository extends JpaRepository<BoxEvent, Long> {

	@Query("SELECT bv FROM BoxEvent as bv WHERE bv.fromBox in :inParameter or bv.toBox in :inParameter")
	 List<BoxEvent> findBoxEvents(@Param("inParameter")Collection<String> inParameter);
	
	@Query("SELECT bv FROM BoxEvent as bv WHERE bv.timestamp < :timestamp")
	 List<BoxEvent> findBoxEventsByTimestamp(@Param("timestamp")Date timestamp);
	
	@Query("SELECT bv FROM BoxEvent as bv WHERE (bv.fromBox in :inParameter or bv.toBox in :inParameter) and bv.timestamp < :timestamp ")
	 List<BoxEvent> findBoxEventsByTimestampAndBoxId(@Param("inParameter")Collection<String> inParameter,@Param("timestamp")Date timestamp); 

}
	
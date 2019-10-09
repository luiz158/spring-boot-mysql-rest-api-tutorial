package com.example.easynotes.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "BoxState")
@EntityListeners(AuditingEntityListener.class)
public class BoxState {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 	
	 	
	 	private String insertionState;
	 	
	 	public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getInsertionState() {
			return insertionState;
		}

		public void setInsertionState(String insertionState) {
			this.insertionState = insertionState;
		}


}

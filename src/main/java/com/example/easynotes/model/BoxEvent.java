package com.example.easynotes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Created by DeepankarShukla.
 */
@Entity
@Table(name = "BoxEvent")
@EntityListeners(AuditingEntityListener.class)
public class BoxEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)   
    @CreationTimestamp  
    private Date timestamp;

   
    private Location location;     

	private String fromBox;
    
    private String toBox;
    
    private String packageType;
    
    private String state;
    
    private String status;
    
    private String errorMessage;


    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getFromBox() {
		return fromBox;
	}

	public void setFromBox(String fromBox) {
		this.fromBox = fromBox;
	}

	public String getToBox() {
		return toBox;
	}

	public void setToBox(String toBox) {
		this.toBox = toBox;
	}

   
}

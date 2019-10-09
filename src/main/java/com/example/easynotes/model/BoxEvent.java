package com.example.easynotes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value = {"location","fromBox","toBox","packageType"},
        allowGetters = true)
public class BoxEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date timestamp;

    @NotBlank
    private Location location;
    
    public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	private String fromBox;
    
    private String toBox;
    
    private String packageType;
    
    private String state;


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

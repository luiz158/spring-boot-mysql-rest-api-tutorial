/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.easynotes.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author PREMENDRA
 */

@Entity
@Table(name = "answers")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "dateCreated", "dateLastModified" }, allowGetters = true)
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date dateCreated;// creation_date

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date dateLastModified;// last_updation_date

	@Column(nullable = false)
	private int rating = 1;// rating

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date dateLastRead;

	@NotBlank
	private String answer;// answer

	@ManyToOne(cascade = CascadeType.ALL)
	private Question question;// linked_cat_id

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Answer ");
		// builder.append("[ansID=");
		// builder.append(ansID);
		// builder.append(", linkedQuesID=");
		// builder.append(linkedQuesID);
		// builder.append(", ");
		if (answer != null) {
			builder.append("answer=");
			builder.append(answer);
			builder.append(", ");
		}
		builder.append("question=");
		builder.append(question);
		builder.append(", ");
		if (dateCreated != null) {
			builder.append("dateCreated=");
			builder.append(dateCreated);
			builder.append(", ");
		}
		if (dateLastModified != null) {
			builder.append("dateLastModified=");
			builder.append(dateLastModified);
			builder.append(", ");
		}
		builder.append("rating=");
		builder.append(rating);
		builder.append(", ");
		if (dateLastRead != null) {
			builder.append("dateLastRead=");
			builder.append(dateLastRead);
			// builder.append(", ");
		}
		// builder.append("totalRead=");
		// builder.append(totalRead);
		builder.append("]");
		return builder.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateLastModified() {
		return dateLastModified;
	}

	public void setDateLastModified(Date dateLastModified) {
		this.dateLastModified = dateLastModified;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getDateLastRead() {
		return dateLastRead;
	}

	public void setDateLastRead(Date dateLastRead) {
		this.dateLastRead = dateLastRead;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}

package de.ankesreich.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MESSAGE")
public class Message {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, updatable = true, length = 100)
	private String message;

	@Column(nullable = false, updatable = true, length = 100)
	private String author;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	public Message() {
	}

	public Message(String author, Date datumCreation, String message) {
		super();
		this.author = author;
		this.creationDate = datumCreation;
		this.message = message;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date datumCreation) {
		this.creationDate = datumCreation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", message=" + message + ", author=" + author + ", datumCreation=" + creationDate
				+ "]";
	}

}

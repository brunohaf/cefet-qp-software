package com.cefetqps.spring.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Post {
	
	private Integer id;

	private Integer userId;

	@DateTimeFormat(iso=ISO.DATE)
	private Date creationDate;

	@DateTimeFormat(iso=ISO.DATE)
	private Date lastEditDate;

	private String title;

	private String content;

	public Post(
		Integer id,
		Integer userId,
		String title,
		String content) {
		
			this.id = id;
			this.userId = userId;
			this.title = title;
			this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastEditDate() {
		return lastEditDate;
	}

	public void setLastEditDate(Date lastEditDate) {
		this.lastEditDate = lastEditDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
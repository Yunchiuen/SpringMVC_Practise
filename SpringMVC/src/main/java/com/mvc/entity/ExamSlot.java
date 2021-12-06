package com.mvc.entity;

public class ExamSlot {
	private String id;
	private String name;

	public ExamSlot() {
	}

	public ExamSlot(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

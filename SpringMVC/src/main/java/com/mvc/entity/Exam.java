package com.mvc.entity;

import java.util.Arrays;
import java.util.Objects;

public class Exam {
	private String id;
	private String name;
	private String[] slot;
	private String pay;
	private String note;
	//將slot陣列轉成字串
	//${ e.slotToString }
	public String getSlotToString() {
		return Arrays.toString(slot);
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

	public String[] getSlot() {
		return slot;
	}

	public void setSlot(String[] slot) {
		this.slot = slot;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(slot);
		result = prime * result + Objects.hash(id, name, note, pay);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exam other = (Exam) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(note, other.note)
				&& Objects.equals(pay, other.pay) && Arrays.equals(slot, other.slot);
	}

}

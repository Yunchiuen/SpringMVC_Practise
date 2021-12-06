package com.mvc.entity.products;

public class Level {
	private Integer lid;
	private String lname;

	public Level() {
	}

	public Level(Integer lid, String lname) {
		this.lid = lid;
		this.lname = lname;
	}

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@Override
	public String toString() {
		return "Level [lid=" + lid + ", lname=" + lname + "]";
	}

}

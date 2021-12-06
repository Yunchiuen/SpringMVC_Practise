package com.mvc.entity.products;

public class Size {
	private Integer sid;
	private String sname;

	public Size() {
	}

	public Size(Integer sid, String sname) {
		super();
		this.sid = sid;
		this.sname = sname;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	@Override
	public String toString() {
		return "Size [sid=" + sid + ", sname=" + sname + "]";
	}

}

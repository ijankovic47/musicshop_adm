package com.musicshop.type;

public class Type {

	private Integer id;
	private String name;
	private String image;
	private Integer instrumentCount;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getInstrumentCount() {
		return instrumentCount;
	}
	public void setInstrumentCount(Integer instrumentCount) {
		this.instrumentCount = instrumentCount;
	}
	
}
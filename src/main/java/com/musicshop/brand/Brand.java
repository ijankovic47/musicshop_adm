package com.musicshop.brand;

public class Brand {

	private Integer id;
	private String name;
	private String image;
	private Integer instrumentCount;
	private Integer totalInstrumentCount;
	
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

	public Integer getTotalInstrumentCount() {
		return totalInstrumentCount;
	}

	public void setTotalInstrumentCount(Integer totalInstrumentCount) {
		this.totalInstrumentCount = totalInstrumentCount;
	}
}

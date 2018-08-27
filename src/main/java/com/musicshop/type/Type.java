package com.musicshop.type;

public class Type {

	private Integer id;
	private String name;
	private String image;
	private Integer instrumentCount;
	private Integer familyId;
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
	public Integer getFamilyId() {
		return familyId;
	}
	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}
	public Integer getTotalInstrumentCount() {
		return totalInstrumentCount;
	}
	public void setTotalInstrumentCount(Integer totalInstrumentCount) {
		this.totalInstrumentCount = totalInstrumentCount;
	}
}

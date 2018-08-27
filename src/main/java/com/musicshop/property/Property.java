package com.musicshop.property;

public class Property {

	private Integer id;
	private String name;
	private Integer instrumentCount;
	private Integer typeId;
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
	public Integer getInstrumentCount() {
		return instrumentCount;
	}
	public void setInstrumentCount(Integer instrumentCount) {
		this.instrumentCount = instrumentCount;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getTotalInstrumentCount() {
		return totalInstrumentCount;
	}
	public void setTotalInstrumentCount(Integer totalInstrumentCount) {
		this.totalInstrumentCount = totalInstrumentCount;
	}
}

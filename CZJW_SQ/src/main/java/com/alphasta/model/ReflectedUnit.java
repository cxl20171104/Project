package com.alphasta.model;

public class ReflectedUnit {
    String id;
    String unitName;
    String unitAddress;
    String unitType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitAddress() {
		return unitAddress;
	}
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	@Override
	public String toString() {
		return "ReflectedUnit [id=" + id + ", unitName=" + unitName + ", unitAddress=" + unitAddress + ", unitType="
				+ unitType + "]";
	}
    
}

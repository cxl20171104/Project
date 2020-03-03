package com.alphasta.model;

public class Lien {
   String id;
   String reflectedId;
   String lienBasis;
   String lienTime;
   String lienRelieveTime;
   String lienDays;
   String delayLien;
   String delayLienDays;
   String delayLienTime;
   String lienReason;
   String time;
   public  Lien() {
		super();
	}
	public Lien(String id, String reflectedId, String lienBasis, String lienTime, String lienRelieveTime,String lienDays,String delayLien,String delayLienDays,String delayLienTime,String lienReason,String time) {
		super();
		this.id = id;
		this.reflectedId = reflectedId;
		this.lienBasis = lienBasis;
		this.lienTime = lienTime;
		this.lienRelieveTime = lienRelieveTime;
		this.lienDays = lienDays;
		this.delayLien = delayLien;
		this.delayLienDays = delayLienDays;
		this.delayLienTime = delayLienTime;
		this.lienReason = lienReason;
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReflectedId() {
		return reflectedId;
	}
	public void setReflectedId(String reflectedId) {
		this.reflectedId = reflectedId;
	}
	public String getLienBasis() {
		return lienBasis;
	}
	public void setLienBasis(String lienBasis) {
		this.lienBasis = lienBasis;
	}
	public String getLienTime() {
		return lienTime;
	}
	public void setLienTime(String lienTime) {
		this.lienTime = lienTime;
	}
	public String getLienRelieveTime() {
		return lienRelieveTime;
	}
	public void setLienRelieveTime(String lienRelieveTime) {
		this.lienRelieveTime = lienRelieveTime;
	}
	public String getLienDays() {
		return lienDays;
	}
	public void setLienDays(String lienDays) {
		this.lienDays = lienDays;
	}
	public String getDelayLien() {
		return delayLien;
	}
	public void setDelayLien(String delayLien) {
		this.delayLien = delayLien;
	}
	public String getDelayLienDays() {
		return delayLienDays;
	}
	public void setDelayLienDays(String delayLienDays) {
		this.delayLienDays = delayLienDays;
	}
	public String getDelayLienTime() {
		return delayLienTime;
	}
	public void setDelayLienTime(String delayLienTime) {
		this.delayLienTime = delayLienTime;
	}
	public String getLienReason() {
		return lienReason;
	}
	public void setLienReason(String lienReason) {
		this.lienReason = lienReason;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}

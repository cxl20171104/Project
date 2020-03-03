package com.alphasta.model;

import java.util.List;

public class TransModel {
	    //进度
	    private List<Progress> progress;
	    //线索
	    private ProblemClues problemClues;
	    private ReflectedPerson reflectedPerson;
	    private ReflectingPerson reflectingPerson;
	    private List<Lien> lien;
	    private List<Punishment> punishment;
	    private User user;
	    private List<Group> group;
	    private List<ZyViolation> zyViolation;
	    private List<SlsResult>   slsResult;
	    private List<LegalAct>    legalAct;
	    private List<Accessories> accessories;
	    private List<Measures>    measures;
	    //操作方式
	    private int option;
	    //目标服务器的ip
	    private String ip;
		public  List<Progress> getProgress() {
			return progress;
		}
		public void setProgress(List<Progress> progress) {
			this.progress = progress;
		}
		public ProblemClues getProblemClues() {
			return problemClues;
		}
		public void setProblemClues(ProblemClues problemClues) {
			this.problemClues = problemClues;
		}
		public ReflectedPerson getReflectedPerson() {
			return reflectedPerson;
		}
		public void setReflectedPerson(ReflectedPerson reflectedPerson) {
			this.reflectedPerson = reflectedPerson;
		}
		public ReflectingPerson getReflectingPerson() {
			return reflectingPerson;
		}
		public void setReflectingPerson(ReflectingPerson reflectingPerson) {
			this.reflectingPerson = reflectingPerson;
		}
		public List<Lien> getLien() {
			return lien;
		}
		public void setLien(List<Lien> lien) {
			this.lien = lien;
		}
		
		public List<Punishment> getPunishment() {
			return punishment;
		}
		public void setPunishment(List<Punishment> punishment) {
			this.punishment = punishment;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public List<Group> getGroup() {
			return group;
		}
		public void setGroup(List<Group> group) {
			this.group = group;
		}
		public int getOption() {
			return option;
		}
		public void setOption(int option) {
			this.option = option;
		}
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public List<ZyViolation> getZyViolation() {
			return zyViolation;
		}
		public void setZyViolation(List<ZyViolation> zyViolation) {
			this.zyViolation = zyViolation;
		}
		public List<SlsResult> getSlsResult() {
			return slsResult;
		}
		public void setSlsResult(List<SlsResult> slsResult) {
			this.slsResult = slsResult;
		}
		public List<LegalAct> getLegalAct() {
			return legalAct;
		}
		public void setLegalAct(List<LegalAct> legalAct) {
			this.legalAct = legalAct;
		}
		public List<Accessories> getAccessories() {
			return accessories;
		}
		public void setAccessories(List<Accessories> accessories) {
			this.accessories = accessories;
		}
		public List<Measures> getMeasures() {
			return measures;
		}
		public void setMeasures(List<Measures> measures) {
			this.measures = measures;
		}
	    
}

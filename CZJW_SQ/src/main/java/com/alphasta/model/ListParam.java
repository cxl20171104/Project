package com.alphasta.model;

import java.util.List;

public class ListParam {
    private List<Progress> progress;
    private ProblemClues problemClues;
    private ReflectedPerson reflectedPerson;
    private ReflectingPerson reflectingPerson;
    private List<Lien> lien;
    private Punishment punishment;
    private User user;
    private List<Group> group;
    private ZyViolation zyViolation;
    private List<LegalAct> legalAct;
    private Measures measures;
    private SlsResult slsResult;
    
	public ReflectedPerson getReflectedPerson() {
		return reflectedPerson;
	}

	public void setReflectedPerson(ReflectedPerson reflectedPerson) {
		this.reflectedPerson = reflectedPerson;
	}

	public ProblemClues getProblemClues() {
		return problemClues;
	}

	public void setProblemClues(ProblemClues problemClues) {
		this.problemClues = problemClues;
	}

	public List<Progress> getProgress() {
		return progress;
	}

	public void setProgress(List<Progress> progress) {
		this.progress = progress;
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

	public Punishment getPunishment() {
		return punishment;
	}

	public void setPunishment(Punishment punishment) {
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

	public ZyViolation getZyViolation() {
		return zyViolation;
	}

	public void setZyViolation(ZyViolation zyViolation) {
		this.zyViolation = zyViolation;
	}

	public List<LegalAct> getLegalAct() {
		return legalAct;
	}

	public void setLegalAct(List<LegalAct> legalAct) {
		this.legalAct = legalAct;
	}
	
	public Measures getMeasures() {
		return measures;
	}

	public void setMeasures(Measures measures) {
		this.measures = measures;
	}
	
	public SlsResult getSlsResult() {
		return slsResult;
	}

	public void setSlsResult(SlsResult slsResult) {
		this.slsResult = slsResult;
	}

	@Override
	public String toString() {
		return "ListParam [progress=" + progress + ", problemClues=" + problemClues + ", reflectedPerson="
				+ reflectedPerson + ", reflectingPerson=" + reflectingPerson + ", lien=" + lien + ", punishment="
				+ punishment + ", user=" + user + ", group=" + group + ", zyViolation=" + zyViolation + ", legalAct="
				+ legalAct + ", measures=" + measures + ", slsResult=" + slsResult + "]";
	}
	
	
	
	
}

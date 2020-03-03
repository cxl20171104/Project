package com.alphasta.model;

import java.util.List;

/**
 * 传输数据的实体类
 * @author cxl
 *
 */
public class HttpModel {
   private ProblemClues problemClues;
   private List<Progress>ProgressList;
   private int which;
public ProblemClues getProblemClues() {
	return problemClues;
}
public void setProblemClues(ProblemClues problemClues) {
	this.problemClues = problemClues;
}
public List<Progress> getProgressList() {
	return ProgressList;
}
public void setProgressList(List<Progress> progressList) {
	ProgressList = progressList;
}
public int getWhich() {
	return which;
}
public void setWhich(int which) {
	this.which = which;
}
   
}

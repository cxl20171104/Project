package com.alphasta.model;
import java.io.Serializable;


/**
 * 
 * 用户足迹
 * 
 */
public class Footprint implements Serializable {

	private static final long serialVersionUID = 6700813629656881143L;
	//用户姓名
	private String name;
	//点赞数
	private Integer like;
	//评论数
	private Integer review;
	//浏览文章数
	private Integer track;
	//发布文章数
	private Integer issue;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLike() {
		return like;
	}
	public void setLike(Integer like) {
		this.like = like;
	}
	public Integer getReview() {
		return review;
	}
	public void setReview(Integer review) {
		this.review = review;
	}
	public Integer getTrack() {
		return track;
	}
	public void setTrack(Integer track) {
		this.track = track;
	}
	public Integer getIssue() {
		return issue;
	}
	public void setIssue(Integer issue) {
		this.issue = issue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Footprint [name=" + name + ", like=" + like + ", review="
				+ review + ", track=" + track + ", issue=" + issue + "]";
	}
	
	
	
}
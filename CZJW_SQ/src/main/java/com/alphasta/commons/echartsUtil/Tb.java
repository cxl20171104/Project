package com.alphasta.commons.echartsUtil;

import java.util.List;
import java.util.Map;

public class Tb {
	 //图表标题
	 private  String text;
	 //副标题
	 private String subtext;
	 //图表图例
	 private  List<Object> legendData;
	 //显示的数据
	 private  List<Object> seriesData;
	 //其它
	 private  Map<String,Object>other;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}


	
	public List<Object> getLegendData() {
		return legendData;
	}
	public void setLegendData(List<Object> legendData) {
		this.legendData = legendData;
	}
	public String getSubtext() {
		return subtext;
	}
	public void setSubtext(String subtext) {
		this.subtext = subtext;
	}


	public List<Object> getSeriesData() {
		return seriesData;
	}
	public void setSeriesData(List<Object> seriesData) {
		this.seriesData = seriesData;
	}
	public Map<String, Object> getOther() {
		return other;
	}
	public void setOther(Map<String, Object> other) {
		this.other = other;
	}
	@Override
	public String toString() {
		return "Tb [text=" + text + ", legendData=" + legendData + ", seriesName=" +  "]";
	}
	   
	}


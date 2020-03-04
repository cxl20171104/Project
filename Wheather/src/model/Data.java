package model;

import java.util.List;

public class Data {
    private String status;
    private List<Hourly>hourly;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Hourly> getHourly() {
		return hourly;
	}
	public void setHourly(List<Hourly> hourly) {
		this.hourly = hourly;
	}
    
}

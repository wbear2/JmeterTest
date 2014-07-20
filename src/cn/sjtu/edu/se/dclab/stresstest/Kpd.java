package cn.sjtu.edu.se.dclab.stresstest;

import java.util.Date;

public class Kpd {

	private int type;
	private Date startTime;
	private int[] records;
	
	public Kpd(int type,Date startTime,int[] records){
		this.type = type;
		this.startTime = startTime;
		this.records = new int[records.length];
		for(int i = 0;i < records.length;i++){
			this.records[i] = records[i];
		}
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public int[] getRecords() {
		return records;
	}
	public void setRecords(int[] records) {
		this.records = records;
	}
	
}

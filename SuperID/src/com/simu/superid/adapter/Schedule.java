package com.simu.superid.adapter;

import java.util.Date;

public class Schedule {
	public Schedule(String actionTime, String alarmTimeDate, String content, boolean isAlarmOn) {
		super();
		this.actionTime = actionTime;
		this.alarmTime = alarmTimeDate;
		this.content = content;
		this.isAlarmOn = isAlarmOn;
	}
	private String actionTime;
	private String alarmTime;
	private String content;
	private boolean isAlarmOn;
	public String getActionTime() {
		return actionTime;
	}
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}
	public String getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTimeDate(String alarmTime) {
		this.alarmTime = alarmTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isAlarmOn() {
		return isAlarmOn;
	}
	public void setAlarmOn(boolean isAlarmOn) {
		this.isAlarmOn = isAlarmOn;
	}
}

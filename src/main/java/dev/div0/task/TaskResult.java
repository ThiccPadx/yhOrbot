package dev.div0.task;

public class TaskResult {
	private String month;
	private String day;
	private String time;
	private String id;
	private String result;
	private String errorText;
	private String payload;
	
	public static String IN_PROGRESS = "IN_PROGRESS";
	public static String COMPLETE = "COMPLETE";
	public static String ERROR = "ERROR";
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
}

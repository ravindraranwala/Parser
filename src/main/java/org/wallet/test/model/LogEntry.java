package org.wallet.test.model;

public class LogEntry {
	private final String date;
	private final String ip;
	private final String request;

	public LogEntry(String date, String ip, String request) {
		super();
		this.date = date;
		this.ip = ip;
		this.request = request;
	}

	public String getDate() {
		return date;
	}

	public String getIp() {
		return ip;
	}

	public String getRequest() {
		return request;
	}
}

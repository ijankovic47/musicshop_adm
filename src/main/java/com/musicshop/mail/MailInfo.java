package com.musicshop.mail;

public class MailInfo {

	private String subject;
	private String from;
	private String[] receivers;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String[] getReceivers() {
		return receivers;
	}
	public void setReceivers(String[] receivers) {
		this.receivers = receivers;
	}
}

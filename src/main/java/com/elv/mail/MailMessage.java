package com.elv.mail;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.mail.internet.MimeMultipart;

public class MailMessage {

	private String smtp;
	private String port;
	private boolean auth;
	private String username;
	private String password;
	private String from;
	private Set<String> to;
	private Set<String> cc;
	private Set<String> bcc;
	private String subject;
	private String body;
	private MimeMultipart mimeMultipart;
	private InputStream contentInputStream;
	
	public String getSmtp() {
		return smtp;
	}
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public boolean isAuth() {
		return auth;
	}
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Set<String> getTo() {
		if(to == null)
			to = new HashSet<String>();
		return to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Set<String> getCc() {
		if(cc == null)
			cc = new HashSet<String>();
		return cc;
	}
	public Set<String> getBcc() {
		if(bcc == null)
			bcc = new HashSet<String>();
		return bcc;
	}
	public MimeMultipart getMimeMultipart() {
		return mimeMultipart;
	}
	public void setMimeMultipart(MimeMultipart mimeMultipart) {
		this.mimeMultipart = mimeMultipart;
	}
	public InputStream getContentInputStream() {
		return contentInputStream;
	}
	public void setContentInputStream(InputStream contentInputStream) {
		this.contentInputStream = contentInputStream;
	}
	
}

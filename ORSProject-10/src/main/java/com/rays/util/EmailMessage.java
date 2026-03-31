package com.rays.util;

import org.springframework.stereotype.Component;

/**
 * Value object holding all configuration for a single outgoing email message,
 * including recipients, subject, body, and message type (HTML or plain text).
 *
 * @author Ajay Pratap Kerketta
 */
@Component
public class EmailMessage {

	/** Comma-separated TO addresses. */
	private String to = null;

	/** Sender address. */
	private String from = null;

	/** Comma-separated CC addresses. */
	private String cc = null;

	/** Comma-separated BCC addresses. */
	private String bcc = null;

	/** Email subject line. */
	private String subject = null;

	/** Email body content. */
	private String message = null;

	/** Constant for HTML message type. */
	public static final int HTML_MSG = 1;

	/** Constant for plain-text message type. */
	public static final int TEXT_MSG = 2;

	/** Message type; defaults to {@link #TEXT_MSG}. */
	private int messageType = TEXT_MSG;

	public String getTo() { return to; }
	public void setTo(String to) { this.to = to; }

	public String getFrom() { return from; }
	public void setFrom(String from) { this.from = from; }

	public String getCc() { return cc; }
	public void setCc(String cc) { this.cc = cc; }

	public String getBcc() { return bcc; }
	public void setBcc(String bcc) { this.bcc = bcc; }

	public String getSubject() { return subject; }
	public void setSubject(String subject) { this.subject = subject; }

	public String getMessage() { return message; }
	public void setMessage(String message) { this.message = message; }

	public int getMessageType() { return messageType; }
	public void setMessageType(int messageType) { this.messageType = messageType; }
}
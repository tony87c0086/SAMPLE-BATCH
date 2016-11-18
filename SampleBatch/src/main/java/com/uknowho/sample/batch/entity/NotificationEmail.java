package com.uknowho.sample.batch.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This NotificationEmail define entity for notificationEmail table.
 * 
 * Created date <08-Sep-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

@Entity
@Table(name="notification_email")
public class NotificationEmail extends GenericEntity<NotificationEmail> implements Serializable {

	private static final long serialVersionUID = 1562205079435903951L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_email_id", nullable = false)
	private Integer notificationEmailID;
	@Column(name = "template_id", nullable = false)
	private Integer templateID;
	@Column(name = "from_email")
	private String fromEmail;
	@Column(name = "to_email")
	private String toEmail;
	@Column(name = "subject")
	private String subject;
	@Column(name = "content")
	private String content;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "active")
	private Boolean active;
	
	@Access(AccessType.PROPERTY)
	@OneToOne(cascade={CascadeType.DETACH, 
			CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinColumn(name="template_id", insertable=false, updatable=false)
	private Template template;
	
	public Integer getNotificationEmailID() {
		return notificationEmailID;
	}

	public void setNotificationEmailID(Integer notificationEmailID) {
		this.notificationEmailID = notificationEmailID;
	}

	public Integer getTemplateID() {
		return templateID;
	}

	public void setTemplateID(Integer templateID) {
		this.templateID = templateID;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("NotificationEmail{");
		sb.append("notificationEmailID:");
		sb.append(notificationEmailID);
		sb.append(", templateID:");
		sb.append(templateID);
		sb.append(", fromEmail:");
		sb.append(fromEmail);
		sb.append(", toEmail:");
		sb.append(toEmail);
		sb.append(", subject:");
		sb.append(subject);
		sb.append(", content:");
		sb.append(content);
		sb.append(", createdDate:");
		sb.append(createdDate);
		sb.append(", active:");
		sb.append(active);
		sb.append("}");
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public NotificationEmail clone() {
		NotificationEmail entity = new NotificationEmail();
		
		entity.setNotificationEmailID(this.notificationEmailID);
		entity.setTemplateID(this.templateID);
		entity.setFromEmail(this.fromEmail);
		entity.setToEmail(this.toEmail);
		entity.setSubject(this.subject);
		entity.setContent(this.content);
		entity.setCreatedDate(this.createdDate);
		entity.setActive(this.active);
		
		return entity;
	}

}

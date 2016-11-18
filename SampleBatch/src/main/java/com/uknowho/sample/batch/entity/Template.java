package com.uknowho.sample.batch.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This Template define entity for template table.
 * 
 * Created date <19-Nov-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

@Entity
@Table(name="template")
public class Template extends GenericEntity<Template> implements Serializable {

	private static final long serialVersionUID = 3607123126237440896L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "template_id", nullable = false)
	private Integer templateID;
	@Column(name = "template_name")
	private String templateName;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "active")
	private Boolean active;

	public Integer getTemplateID() {
		return templateID;
	}

	public void setTemplateID(Integer templateID) {
		this.templateID = templateID;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
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

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Template{");
		sb.append("templateID:");
		sb.append(templateID);
		sb.append(", templateName:");
		sb.append(templateName);
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
	public Template clone() {
		Template entity = new Template();
		
		entity.setTemplateID(this.templateID);
		entity.setTemplateName(this.templateName);
		entity.setCreatedDate(this.createdDate);
		entity.setActive(this.active);
		
		return entity;
	}

}

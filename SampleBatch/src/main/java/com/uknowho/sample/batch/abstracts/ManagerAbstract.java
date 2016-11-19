package com.uknowho.sample.batch.abstracts;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uknowho.sample.batch.constant.ErrorCodeConstant;
import com.uknowho.sample.batch.constant.ErrorMessageConstant;
import com.uknowho.sample.batch.entity.NotificationEmail;
import com.uknowho.sample.batch.entity.Template;
import com.uknowho.sample.batch.exception.ServiceException;
import com.uknowho.sample.batch.utility.DateAdapter;
import com.uknowho.sample.batch.xmlmodel.NotificationEmailModel;
import com.uknowho.sample.batch.xmlmodel.TemplateModel;

/**
 * This ManagerAbstract class is Defines manager common function 
 * 
 * Created date <19-Nov-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public abstract class ManagerAbstract {

	private static final Logger logger = LoggerFactory.getLogger(ManagerAbstract.class);
	
	protected ManagerAbstract() {
		//	logger.info("ManagerAbstract construction method load.");
	}
	
	/** --------------------------------------------------------------------------------
	 * 									Template 
	 * 	--------------------------------------------------------------------------------
	 */
	
	// Copy contact template from entity to model
	protected TemplateModel popularTemplateToModel(final Template entity) 
			throws IllegalArgumentException, ServiceException {
		TemplateModel target = null;
		try {
			if (entity == null) {
				throw new ServiceException(ErrorCodeConstant.ENTITY_NOT_FOUND, 
						ErrorMessageConstant.ENTITY_NOT_FOUND);
			}
			
			target = new TemplateModel();
			
			target.setTemplateID(entity.getTemplateID());
			target.setTemplateName(entity.getTemplateName());
			target.setCreatedDate(DateAdapter.printDateTime(entity.getCreatedDate()));
			target.setActive(entity.getActive());
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new IllegalArgumentException(ErrorMessageConstant.
					COPY_ENTITY_TO_MODEL_EXCEPTION);
		} 
		return target;
	}
	
	// Copy template information from model to entity
	protected Template loadTemplateFromModel(
			final Template entity,
			final TemplateModel model) throws IllegalArgumentException, ServiceException {
		try {
			// Data integrity Check begin
			if (entity == null) {
				throw new ServiceException(ErrorCodeConstant.ENTITY_NOT_FOUND, 
						ErrorMessageConstant.ENTITY_NOT_FOUND);
			}
			
			if (model == null) {
				throw new ServiceException(ErrorCodeConstant.MODEL_NOT_FOUND, 
						ErrorMessageConstant.MODEL_NOT_FOUND);
			}
			// Data integrity Check end
			
			entity.setTemplateID(model.getTemplateID());
			entity.setTemplateName(model.getTemplateName());
			
			Date createDate = DateAdapter.parseDateTime(model.getCreatedDate());
			if (createDate == null) {
				createDate = DateAdapter.getDateTime();
			}
			entity.setCreatedDate(createDate);
			
			entity.setActive(model.isActive());
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new IllegalArgumentException(ErrorMessageConstant.
					COPY_ENTITY_FROM_MODEL_EXCEPTION);
		} 
		return entity;
	}
	
	// Create new template entity based on model
	protected Template buildTemplate(final TemplateModel model) 
			throws IllegalArgumentException, ServiceException {
		Template target = null;
		try {
			// Data integrity Check 
			if (model == null) {
				throw new ServiceException(ErrorCodeConstant.MODEL_NOT_FOUND, 
						ErrorMessageConstant.MODEL_NOT_FOUND);
			}
			
			target = new Template();
			
			if (model.getTemplateID() != null) {
				target.setTemplateID(model.getTemplateID());
			} 
			
			// Copy contact details from model
			loadTemplateFromModel(target, model);
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new IllegalArgumentException(ErrorMessageConstant.
					CREATE_ENTITY_EXCEPTION);
		} 
		return target;
	}
	
	/** --------------------------------------------------------------------------------
	 * 									NotificationEmail 
	 * 	--------------------------------------------------------------------------------
	 */
	
	// Copy contact notificationEmail from entity to model
	protected NotificationEmailModel popularNotificationEmailToModel(final NotificationEmail entity) 
			throws IllegalArgumentException, ServiceException {
		NotificationEmailModel target = null;
		try {
			if (entity == null) {
				throw new ServiceException(ErrorCodeConstant.ENTITY_NOT_FOUND, 
						ErrorMessageConstant.ENTITY_NOT_FOUND);
			}
			
			target = new NotificationEmailModel();
			
			target.setNotificationEmailID(entity.getNotificationEmailID());
			target.setTemplateID(entity.getTemplateID());
			target.setFromEmail(entity.getFromEmail());
			target.setToEmail(entity.getToEmail());
			target.setSubject(entity.getSubject());
			target.setContent(entity.getContent());
			target.setCreatedDate(DateAdapter.printDateTime(entity.getCreatedDate()));
			target.setActive(entity.getActive());
			
			if (entity.getTemplate() != null) {
				target.setTemplate(popularTemplateToModel(entity.getTemplate()));
			}
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new IllegalArgumentException(ErrorMessageConstant.
					COPY_ENTITY_TO_MODEL_EXCEPTION);
		} 
		return target;
	}
	
	// Copy notificationEmail information from model to entity
	protected NotificationEmail loadNotificationEmailFromModel(
			final NotificationEmail entity,
			final NotificationEmailModel model) throws IllegalArgumentException, ServiceException {
		try {
			// Data integrity Check begin
			if (entity == null) {
				throw new ServiceException(ErrorCodeConstant.ENTITY_NOT_FOUND, 
						ErrorMessageConstant.ENTITY_NOT_FOUND);
			}
			
			if (model == null) {
				throw new ServiceException(ErrorCodeConstant.MODEL_NOT_FOUND, 
						ErrorMessageConstant.MODEL_NOT_FOUND);
			}
			// Data integrity Check end
			
			entity.setNotificationEmailID(model.getNotificationEmailID());
			entity.setTemplateID(model.getTemplateID());
			entity.setFromEmail(model.getFromEmail());
			entity.setToEmail(model.getToEmail());
			entity.setSubject(model.getSubject());
			entity.setContent(model.getContent());
			
			Date createDate = DateAdapter.parseDateTime(model.getCreatedDate());
			if (createDate == null) {
				createDate = DateAdapter.getDateTime();
			}
			entity.setCreatedDate(createDate);
			
			entity.setActive(model.isActive());
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new IllegalArgumentException(ErrorMessageConstant.
					COPY_ENTITY_FROM_MODEL_EXCEPTION);
		} 
		return entity;
	}
	
	// Create new notificationEmail entity based on model
	protected NotificationEmail buildNotificationEmail(final NotificationEmailModel model) 
			throws IllegalArgumentException, ServiceException {
		NotificationEmail target = null;
		try {
			// Data integrity Check 
			if (model == null) {
				throw new ServiceException(ErrorCodeConstant.MODEL_NOT_FOUND, 
						ErrorMessageConstant.MODEL_NOT_FOUND);
			}
			
			target = new NotificationEmail();
			
			if (model.getNotificationEmailID() != null) {
				target.setNotificationEmailID(model.getNotificationEmailID());
			} 
			
			// Copy contact details from model
			loadNotificationEmailFromModel(target, model);
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new IllegalArgumentException(ErrorMessageConstant.
					CREATE_ENTITY_EXCEPTION);
		} 
		return target;
	}
	
}

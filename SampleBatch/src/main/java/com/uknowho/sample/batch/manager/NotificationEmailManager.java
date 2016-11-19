package com.uknowho.sample.batch.manager;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uknowho.sample.batch.abstracts.ManagerAbstract;
import com.uknowho.sample.batch.constant.ErrorCodeConstant;
import com.uknowho.sample.batch.constant.ErrorMessageConstant;
import com.uknowho.sample.batch.dao.NotificationEmailDao;
import com.uknowho.sample.batch.entity.NotificationEmail;
import com.uknowho.sample.batch.exception.ServiceException;
import com.uknowho.sample.batch.utility.DataFormat;
import com.uknowho.sample.batch.xmlmodel.PaginationModel;
import com.uknowho.sample.batch.xmlmodel.SortModel;
import com.uknowho.sample.batch.xmlmodel.NotificationEmailModel;

/**
 * This NotificationEmailManager class is Defines for notificationEmail related methods.
 * 
 * 
 * Created date <19-Nov-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

@Service("notificationEmailManager")
public class NotificationEmailManager extends ManagerAbstract {
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationEmailManager.class);
	
	@Autowired
	private NotificationEmailDao notificationEmailDao;
	
	public NotificationEmailManager() {
		logger.info("NotificationEmailManager default constrction method load.");
	}
	
	// Fetch exist notificationEmail 
	public NotificationEmailModel fetchNotificationEmailModel(final Integer notificationEmailID)
			throws ServiceException {
		NotificationEmailModel target = null;
		try {
			
			NotificationEmail entity = notificationEmailDao.get(notificationEmailID);
			
			if (entity == null) {
				throw new ServiceException(ErrorCodeConstant.NOTIFICATION_EMAIL_NOT_FOUND, 
						ErrorMessageConstant.NOTIFICATION_EMAIL_NOT_FOUND);
			} 
			
			target = popularNotificationEmailToModel(entity);

		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.MODEL_MAPPING_EXCEPTION, 
					e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} 
		return target;
	}
	
	// List all notificationEmail notificationEmail model
	public List<NotificationEmailModel> listNotificationEmailModel() throws ServiceException {
		return listNotificationEmailModel(null, null, null, null);
	}
	
	/**
	 * @param templateID
	 * @param active
	 * @return List<NotificationEmailModel>
	 * @throws ServiceException
	 */
	public List<NotificationEmailModel> listNotificationEmailModel(
			final Integer templateID, final Boolean active) throws ServiceException {
		return listNotificationEmailModel(templateID, active, null, null);
	}
		
	/**
	 * @param templateID
	 * @param active
	 * @param sortList
	 * @param pagination
	 * @return List<NotificationEmailModel>
	 * @throws ServiceException
	 */
	public List<NotificationEmailModel> listNotificationEmailModel(
			final Integer templateID,
			final Boolean active,
			final List<SortModel> sortList,
			final PaginationModel pagination) throws ServiceException {
		List<NotificationEmailModel> targetList = null;
		try {
			
			targetList = new ArrayList<NotificationEmailModel>();
			
			List<NotificationEmail> notificationEmailList = null;
			if ((DataFormat.isListValid(sortList)) || (pagination != null)) {
				notificationEmailList = notificationEmailDao.list(
						templateID,
						active, 
						sortList,
						pagination);
			} else if ((templateID != null) || (active != null)) {
				notificationEmailList = notificationEmailDao.list(templateID, active);
			} else {
				notificationEmailList = notificationEmailDao.list();
			}
			
			if (DataFormat.isListValid(notificationEmailList)) {
				for (NotificationEmail notificationEmailEntity : notificationEmailList) {
					if (notificationEmailEntity != null) {
						targetList.add(popularNotificationEmailToModel(notificationEmailEntity));
					}
				}
			}
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.MODEL_MAPPING_EXCEPTION, 
					e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} finally {
			if (targetList == null) {
				targetList = new ArrayList<NotificationEmailModel>();
			}
		}
		return targetList;
	}

	
	public List<Integer> updateNotificationEmailModelList(
			final List<NotificationEmailModel> modelList, 
			final Boolean master) throws ServiceException {
		List<Integer> notificationEmailIDList = null;
		if (DataFormat.isListValid(modelList)) {
			notificationEmailIDList = new ArrayList<Integer>();
			Integer notificationEmailID = null;
			for (NotificationEmailModel model : modelList) {
				if (model != null) {
					notificationEmailID = updateNotificationEmailModel(model, master);
					notificationEmailIDList.add(notificationEmailID);
				}
			}
		}
		return notificationEmailIDList;
	}
	
	// Update existing notificationEmail in DB
	public Integer updateNotificationEmailModel(
			final NotificationEmailModel model, 
			final Boolean master) throws ServiceException {
		Integer notificationEmailID = null;
		try {
			// Data integrity Check 
			if (model == null) {
				throw new ServiceException(ErrorCodeConstant.NOTIFICATION_EMAIL_MODEL_NOT_FOUND, 
						ErrorMessageConstant.NOTIFICATION_EMAIL_MODEL_NOT_FOUND);
			}
			
			notificationEmailID = model.getNotificationEmailID();
			NotificationEmail entity = notificationEmailDao.get(notificationEmailID);
			
			if (entity == null) {
				throw new ServiceException(ErrorCodeConstant.NOTIFICATION_EMAIL_NOT_FOUND, 
						ErrorMessageConstant.NOTIFICATION_EMAIL_NOT_FOUND);
			}
			
			entity = loadNotificationEmailFromModel(entity, model);
			entity = notificationEmailDao.update(entity);

			if (entity != null) {
				notificationEmailID = entity.getNotificationEmailID();
			}
			
		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.MODEL_MAPPING_EXCEPTION, 
					e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} 
		return notificationEmailID;
	}
	
	public boolean deleteNotificationEmailModelList(final List<Integer> notificationEmailIDList)
			throws ServiceException {
		boolean isSuccess = false;
		if (DataFormat.isListValid(notificationEmailIDList)) {
			for (Integer notificationEmailID : notificationEmailIDList) {
				if (notificationEmailID != null) {
					deleteNotificationEmailModel(notificationEmailID);
				}
			}
		}
		isSuccess = true;
		return isSuccess;
	}
	
	// Delete existing notificationEmail in DB
	public boolean deleteNotificationEmailModel(final Integer notificationEmailID)
			throws ServiceException {
		boolean isSuccess = false;
		try {
			// Data integrity Check 
			if (notificationEmailID == null) {
				throw new ServiceException(ErrorCodeConstant.NOTIFICATION_EMAIL_NOT_FOUND, 
						ErrorMessageConstant.NOTIFICATION_EMAIL_NOT_FOUND);
			}
			
			isSuccess = notificationEmailDao.delete(notificationEmailID);

		} catch (ServiceException e) {
			logger.error(e.toString());
			throw new ServiceException(e.getErrorCode(), e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.MODEL_MAPPING_EXCEPTION, 
					e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServiceException(ErrorCodeConstant.UNKNOWN_EXCEPTION, 
					ErrorMessageConstant.UNKNOWN_EXCEPTION);
		} 
		return isSuccess;
	}
	
}
